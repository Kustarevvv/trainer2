package ru.kustarevvv.web.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.kustarevvv.domain.service.QuestionService;
import ru.kustarevvv.web.api.dto.OpenQuestionCardCreateDto;
import ru.kustarevvv.web.api.dto.OpenQuestionCardDto;
import ru.kustarevvv.web.api.mapper.QuestionDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
@Tag(name = "Questions", description = "Endpoint-ы, связанные с вопросами")
public class QuestionRestController {
    private final QuestionService questionService;
    private final QuestionDtoMapper mapper;

    public QuestionRestController(QuestionService questionService, QuestionDtoMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @Operation(summary = "Получение всех вопросов",
            description = "Получены все вопросы пользователя без ограничений"
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OpenQuestionCardDto> list() {
        return mapper.mapToDto(questionService.getAll());
    }

    @Operation(summary = "Добавление нового вопроса",
            description = "Создает новый вопрос"
    )
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void add(@Parameter(description = "Новая задача") @RequestBody OpenQuestionCardCreateDto dto) {
        questionService.save(mapper.mapToModel(dto));
    }

    @Operation(summary = "Обновление вопроса",
            description = "Находит вопрос и обновляет"
    )
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void update(@Parameter(description = "Измененный вопрос") @RequestBody OpenQuestionCardDto dto) {
        questionService.save(mapper.mapToModel(dto));
    }

    @Operation(summary = "Удаление вопроса",
            description = "Находит вопрос по id и удаляет, если он найден"
    )
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "Id вопроса для удаления") @PathVariable Long id) {
        questionService.getById(id).ifPresent(questionService::delete);
    }
}
