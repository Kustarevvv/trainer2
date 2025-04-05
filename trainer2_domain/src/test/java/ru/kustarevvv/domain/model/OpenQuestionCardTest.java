package ru.kustarevvv.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OpenQuestionCardTest {
    private static final Long ID = 123456L;
    private static final String QUESTION = "Вопрос";
    private static final String ANSWER = "Ответ";
    private OpenQuestionCard card;

    @BeforeEach
    void setUp() {
        card = new OpenQuestionCard(ID, QUESTION, ANSWER);
    }

    @ParameterizedTest
    @MethodSource("invalidConstructorArgs")
    void constructor_WithInvalidArgs_ThrowsException(Long id, String question, String expectedAnswer) {
        assertThrows(IllegalArgumentException.class, () -> new OpenQuestionCard(id, question, expectedAnswer));
    }

    private static Stream<Arguments> invalidConstructorArgs() {
        return Stream.of(
                Arguments.of(ID, null, ANSWER),
                Arguments.of(ID, "", ANSWER),
                Arguments.of(ID, " ", ANSWER),
                Arguments.of(ID, QUESTION, null),
                Arguments.of(ID, QUESTION, ""),
                Arguments.of(ID, QUESTION, " ")
        );
    }

    @Test
    void checkAnswer_ReturnsCorrectResult() {
        assertTrue(card.checkAnswer(ANSWER));
        assertFalse(card.checkAnswer("Ответ2"));
    }

    @Test
    void getters_ReturnCorrectValues() {
        assertEquals(ID, card.getId());
        assertEquals(QUESTION, card.getQuestion());
        assertEquals(ANSWER, card.getExpectedAnswer());
    }
}