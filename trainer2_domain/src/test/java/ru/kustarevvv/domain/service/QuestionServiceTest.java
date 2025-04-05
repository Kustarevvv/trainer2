package ru.kustarevvv.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;

import java.util.Optional;

public class QuestionServiceTest {
    private QuestionService questionService;
    private QuestionRepository questionRepository;
    @BeforeEach
    void setUp() {
        questionRepository = Mockito.mock(QuestionRepository.class);
        questionService = new QuestionService(questionRepository);
    }

    @Test
    @DisplayName("Получение существующего вопроса по id")
    void having_taskInRepo_when_getById_then_returnTask() {
        Mockito.when(questionRepository.findById(12L))
                .thenReturn(Optional.of(new OpenQuestionCard(12L, "Вопрос 1", "Ответ 1")));
        Optional<OpenQuestionCard> openQuestionCard = questionService.getById(12L);
        Assertions.assertTrue(openQuestionCard.isPresent());
    }

    @Test
    @DisplayName("При сохранении нового вопроса, вызывается метод add у репозитория")
    void having_noQuestionInRepo_when_save_then_repoAddIsInvoken() {
        OpenQuestionCard openQuestionCard = new OpenQuestionCard(12L, "Вопрос 1", "Ответ 1");
        questionService.save(openQuestionCard);
        Mockito.verify(questionRepository).save(Mockito.any());
    }

}
