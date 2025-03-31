package ru.kustarevvv.web.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на создание вопроса")
public class OpenQuestionCardCreateDto {
    @Schema(description = "Текст вопроса", example = "Что такое конструктор в Java?")
    private String question;

    @Schema(description = "Предполагаемый ответ", example = "Специальный метод класса, который используется для инициализации полей объекта и выполнения других начальных настроек.")
    private String expectedAnswer;

    public OpenQuestionCardCreateDto() {
    }

    public OpenQuestionCardCreateDto(String question, String expectedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setExpectedAnswer(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }
}