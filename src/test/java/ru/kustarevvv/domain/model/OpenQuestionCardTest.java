package ru.kustarevvv.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class OpenQuestionCardTest {

    private static final String CARD_QUESTION = "Конструктор класса в Java это?";

    private static final String CARD_EXPECTED_ANSWER = "Cпециальный блок кода, похожий на метод, предназначенный для инициализации полей объекта при его создании?";

    private OpenQuestionCard openQuestionCard;

    @BeforeEach
    void setUp() {
        openQuestionCard = new OpenQuestionCard(CARD_QUESTION, CARD_EXPECTED_ANSWER);
    }

    @Test
    @DisplayName("Создание OpenquestionCard с корректными question и expectedQuestion проходит успешно")
    void having_correctQuestion_when_newOpenQuestionCard_then_created(){
        Assertions.assertEquals(CARD_QUESTION, openQuestionCard.getQuestion());
    }

    @Test
    @DisplayName("Создание OpenQuestionCard с question равным null выбрасывает исключение")
    void having_nullQuestion_when_newOpenQuestionCard_then_exceptionThrown() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new OpenQuestionCard(null, CARD_EXPECTED_ANSWER));
    }

    @Test
    @DisplayName("Создание OpenQuestionCard с expected_answer равным null выбрасывает исключение")
    void having_nullExpectedAnswer_when_newOpenQuestionCard_then_exceptionThrown() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new OpenQuestionCard(CARD_QUESTION, null));
    }

    @Test
    @DisplayName("Создание OpenQuestionCard с пустым полем question выбрасывает исключение")
    void having_emptyQuestion_when_newOpenQuestionCard_then_exceptionThrown() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new OpenQuestionCard("", CARD_EXPECTED_ANSWER));
    }

    @Test
    @DisplayName("Создание OpenQuestionCard с пустым полем expected_answer выбрасывает исключение")
    void having_emptyExpectedAnswer_when_newOpenQuestionCard_then_exceptionThrown() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new OpenQuestionCard(CARD_QUESTION, ""));
    }

    @Test
    @DisplayName("Проверка ответа методом checkAnswer() проходит успешно")
    void having_correctResult_while_using_checkAnswer_method() {
        String answer = "Неверный ответ";
        Assertions.assertTrue(openQuestionCard.checkAnswer(CARD_EXPECTED_ANSWER));
        Assertions.assertFalse(openQuestionCard.checkAnswer(answer));
    }
}