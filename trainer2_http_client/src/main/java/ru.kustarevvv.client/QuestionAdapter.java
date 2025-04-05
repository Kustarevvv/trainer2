package ru.kustarevvv.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;

import ru.kustarevvv.web.api.dto.OpenQuestionCardDto;
import ru.kustarevvv.web.api.mapper.QuestionDtoMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.ErrorManager;
@Repository
public class QuestionAdapter implements QuestionRepository {

    private static final String QUESTION_ROOT_URL_TEMPLATE = "%s/question";
    private static final String QUESTION_DELETE_URL_TEMPLATE = "%s/question/%s";
    private final HttpClient httpClient;
    private final String serverUrl;
    private final QuestionDtoMapper mapper;
    private final Gson gson;

    private static final Logger logger = LogManager.getLogger(QuestionAdapter.class);

    public QuestionAdapter(@Value("${trainer2.interactions.server_url}") String serverUrl,
                           QuestionDtoMapper mapper, Gson gson) {
        this.gson = gson;
        httpClient = HttpClient.newBuilder()
                .build();
        this.serverUrl = serverUrl;
        this.mapper = mapper;
    }
    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCard> questions = Collections.emptyList();
        try {
            String url = String.format(QUESTION_ROOT_URL_TEMPLATE, serverUrl);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url)).GET().build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (validateResponse(response)) {
                List<OpenQuestionCardDto> dtos = gson.fromJson(response.body(),
                        new TypeToken<List<OpenQuestionCardDto>>(){}.getType());
                questions = mapper.mapToModel(dtos);
            }
        } catch (IOException | InterruptedException e) {}
        return questions;
    }

    private boolean validateResponse(HttpResponse<String> response) {
        return response != null
                && response.statusCode() == 200
                && response.body() != null
                && !"".equals(response.body());
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void add(OpenQuestionCard openQuestionCard) {
        OpenQuestionCardDto dto = mapper.mapToDto(openQuestionCard);
        String body = gson.toJson(dto);
        try {
            String url = String.format(QUESTION_ROOT_URL_TEMPLATE, serverUrl);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .headers("Content-Type", "application/json;charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(OpenQuestionCard openQuestionCard) {
        OpenQuestionCardDto dto = mapper.mapToDto(openQuestionCard);
        String body = gson.toJson(dto);
        try {
            String url = String.format(QUESTION_ROOT_URL_TEMPLATE, serverUrl);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .headers("Content-Type", "application/json;charset=UTF-8")
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void remove(Long id) {
        try {
            String url = String.format(QUESTION_DELETE_URL_TEMPLATE, serverUrl, id);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void save(OpenQuestionCard card) {

    }
}
