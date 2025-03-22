package ru.kustarevvv.domain.model;

public class OpenQuestionCard {

    private final Long id;
    private final String question;

    private final String expectedAnswer;

    public OpenQuestionCard(Long id, String question, String expectedAnswer) {

        if (question == null || question.trim().isEmpty()){
            throw new IllegalArgumentException("Значение поля question не должно быть пустым или null");
        }

        if (expectedAnswer == null || expectedAnswer.trim().isEmpty()){
            throw new IllegalArgumentException("Значение поля expectedAnswer не должно быть пустым или null");
        }
        if (id == null){
            throw new IllegalArgumentException("Значение поля id не должно быть null");
        }
        this.id = id;
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }
//    public OpenQuestionCard(Long id, String question){
//        if (question == null || question.trim().isEmpty()){
//            throw new IllegalArgumentException("Значение поля question не должно быть пустым или null");
//        }
//
//        if (expectedAnswer == null || expectedAnswer.trim().isEmpty()){
//            throw new IllegalArgumentException("Значение поля expectedAnswer не должно быть пустым или null");
//        }
//
//        this.id = id;
//        this.question = question;
//
//    }

    public String getQuestion() {
        return question;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public Long getId() {
        return id;
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
