package ru.kustarevvv.storage;

import java.util.List;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class QuestionlnMemoryStorage implements QuestionRepository {

    private final Map<Long, OpenQuestionCard> openQuestionCards;
    public QuestionlnMemoryStorage() {
        openQuestionCards = new HashMap<>();
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        return (List<OpenQuestionCard>) openQuestionCards.values().stream().toList();
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        OpenQuestionCard foundOpenQuestionCard = openQuestionCards.get(id);
        if (Objects.nonNull(foundOpenQuestionCard)) {
            return Optional.of(foundOpenQuestionCard);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void add(OpenQuestionCard openQuestionCard) {
        openQuestionCards.put(openQuestionCard.getId(), openQuestionCard);
    }

    @Override
    public void update(OpenQuestionCard openQuestionCard) {
        openQuestionCards.put(openQuestionCard.getId(), openQuestionCard);
    }

    @Override
    public void remove(Long id) {
        openQuestionCards.remove(id);
    }

    @Override
    public void save(OpenQuestionCard card) {

    }
}
