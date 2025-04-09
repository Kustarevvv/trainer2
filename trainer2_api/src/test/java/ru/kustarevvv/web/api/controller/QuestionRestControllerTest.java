package ru.kustarevvv.web.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;
import ru.kustarevvv.web.api.dto.OpenQuestionCardCreateDto;
import ru.kustarevvv.web.api.dto.OpenQuestionCardDto;
import ru.kustarevvv.web.gui.Application;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)

class QuestionRestControllerTest {
    @MockitoBean
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionRestController controller;

    OpenQuestionCardCreateDto dto;
    @Test
    @DisplayName("Создание Question с корректными id, question, expectedAnswer проходит успешно")
    void having_question_when_list_then_return() {
        Mockito.when(questionRepository.findAll())
                .thenReturn(List.of(new OpenQuestionCard(12L, "Вопрос 1", "Ответ 1")));
        List<OpenQuestionCardDto> list = controller.list();
        Assertions.assertEquals(1, list.size());
    }

}