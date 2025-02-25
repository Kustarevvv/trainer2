package ru.kustarevvv.domain.model;

public class OpenQuestionCard {

    private final String question;

    private final String expectedAnswer;

    public OpenQuestionCard(String question, String expectedAnswer) {
        if (question == null || question.trim().isEmpty()){
            throw new IllegalArgumentException("Значение поля question не должно быть пустым или null");
        }

        if (expectedAnswer == null || expectedAnswer.trim().isEmpty()){
            throw new IllegalArgumentException("Значение поля expectedAnswer не должно быть пустым или null");
        }

        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(String userAnswer) {
        return expectedAnswer.equals(userAnswer);
    }

    @Override
    public String toString() {
        return "OpenQuestionCard{" +
                "question='" + question + '\'' +
                ", expectedAnswer='" + expectedAnswer + '\'' +
                '}';
    }
}
