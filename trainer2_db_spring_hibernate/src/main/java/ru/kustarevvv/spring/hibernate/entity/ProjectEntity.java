package ru.kustarevvv.spring.hibernate.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String question;

    @Column
    private String expectedAnswer;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<OpenQuestionCardEntity> openQuestionCards;

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public List<OpenQuestionCardEntity> getOpenQuestionCards() {
        return openQuestionCards;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setExpectedAnswer(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    public void setOpenQuestionCards(List<OpenQuestionCardEntity> openQuestionCards) {
        this.openQuestionCards = openQuestionCards;
    }
}
