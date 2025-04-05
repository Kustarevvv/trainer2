package ru.kustarevvv.domain.service;

import org.springframework.stereotype.Component;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;

import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;


@Service
public class QuestionService {

    private final QuestionRepository repository;
    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }
    public List<OpenQuestionCard> getAll() {
        return repository.findAll();
    }
    public Optional<OpenQuestionCard> getById(Long id) {
        if (Objects.isNull(id)) {
            return Optional.empty();
        }
        return repository.findById(id);
    }
    public boolean contains(OpenQuestionCard openQuestionCard) {
        if (isOpenCardQuestionInvalid(openQuestionCard)) {
            return false;
        }
        return repository.findById(openQuestionCard.getId()).isPresent();
    }
    public void save(OpenQuestionCard openQuestionCard) {
        //заменил
        if (openQuestionCard == null) return;
        repository.save(openQuestionCard);
        //заменил
    }
    public void delete(OpenQuestionCard openQuestionCard) {
        if (isOpenCardQuestionInvalid(openQuestionCard)) {
            return;
        }
        repository.remove(openQuestionCard.getId());
    }
    private boolean isOpenCardQuestionInvalid(OpenQuestionCard openQuestionCard) {
        return Objects.isNull(openQuestionCard) || Objects.isNull(openQuestionCard.getId());
    }
}
