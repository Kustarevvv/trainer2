package ru.kustarevvv.domain.model;

import ru.kustarevvv.domain.utility.ValidationUtil;

import java.util.List;

public class Project {
    private final Long id;
    private final String question;
    private final String expectedAnswer;
    private final List<OpenQuestionCard> openQuestionCards;

    public Project(Long id, String question, String expectedAnswer, List<OpenQuestionCard> openQuestionCards) {
        this.id = id;
        this.question = question;
        this.expectedAnswer = expectedAnswer;
        this.openQuestionCards = openQuestionCards;

        ValidationUtil.validateNotBlank(question, "question не может быть пустым");
        ValidationUtil.validateNotBlank(expectedAnswer, "expectedAnswer не может быть пустым");
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public List<OpenQuestionCard> getOpenQuestionCards() {
        return openQuestionCards;
    }
    public void addOpenQuestionCard(OpenQuestionCard openQuestionCard){
        openQuestionCards.add(openQuestionCard);
    }

    public void removeOpenQuestionCard(OpenQuestionCard openQuestionCard){
        openQuestionCards.remove(openQuestionCard);
    }
}
