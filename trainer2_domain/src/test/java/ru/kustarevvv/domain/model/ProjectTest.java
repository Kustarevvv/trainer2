package ru.kustarevvv.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    @ParameterizedTest
    @MethodSource("provideIncorrectInput")
    @DisplayName("Создание Project с некорректными данными завершается ошибкой")
    void having_incorrectInput_when_newProject_then_exception(Long id, String question, String expectedAnswer, List<OpenQuestionCard> questions) {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new Project(id, question, expectedAnswer, questions));
    }

    private static Stream<Arguments> provideIncorrectInput() {
        return Stream.of(
                //Arguments.of(null, "Valid question", "Valid answer", List.of()),
                Arguments.of(1L, null, "Valid answer", List.of()),
                Arguments.of(1L, "", "Valid answer", List.of()),
                Arguments.of(1L, "   ", "Valid answer", List.of()),
                Arguments.of(1L, "Valid question", null, List.of()),
                Arguments.of(1L, "Valid question", "", List.of()),
                Arguments.of(1L, "Valid question", "   ", List.of())
        );
    }




}