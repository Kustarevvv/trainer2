package ru.kustarevvv.spring.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionJdbcTemplateDao implements QuestionRepository {

    private static final String DDL_QUERY = "Create table openQuestionCards (id int AUTO_INCREMENT primary key, question varchar(100), expectedAnswer varchar(100))";
    private static final String FIND_ALL_QUERY = "SELECT id, question, expectedAnswer FROM openQuestionCards";
    private static final String FIND_BY_ID_QUERY = "SELECT id, question, expectedAnswer FROM openQuestionCards WHERE id = ?";
    private static final String INSERT_QUESTION_QUERY = "INSERT INTO openQuestionCards(id,question, expectedAnswer) VALUES (?,?,?)";
    private static final String UPDATE_QUESTION_QUERY = "UPDATE openQuestionCards SET question=?, expectedAnswer =?";
    private static final String DELETE_QUESTION_QUERY = "DELETE FROM openQuestionCards WHERE id=?";
    private final JdbcTemplate jdbcTemplate;

    public QuestionJdbcTemplateDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        initSchema();
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY,
                (ResultSet rs, int rowNum) ->
                        new OpenQuestionCard(Long.parseLong(rs.getString("id")), rs.getString("question"), rs.getString("expectedAnswer")));
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        List<OpenQuestionCard> openQuestionCards = jdbcTemplate.query(FIND_BY_ID_QUERY,
                (ResultSet rs, int rowNum) ->
                        new OpenQuestionCard(Long.parseLong(rs.getString("id")), rs.getString("question"), rs.getString("expectedAnswer")), id.toString());
        return openQuestionCards.isEmpty() ? Optional.empty() : Optional.of(openQuestionCards.get(0));
    }

    @Override
    public void add(OpenQuestionCard openQuestionCard) {
        jdbcTemplate.update(INSERT_QUESTION_QUERY, openQuestionCard.getId(),openQuestionCard.getQuestion(), openQuestionCard.getExpectedAnswer());
    }

    @Override
    public void update(OpenQuestionCard openQuestionCard) {
        jdbcTemplate.update(UPDATE_QUESTION_QUERY, openQuestionCard.getQuestion(), openQuestionCard.getExpectedAnswer());
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(DELETE_QUESTION_QUERY, id);
    }

    @Override
    public void save(OpenQuestionCard card) {

    }

    private void initSchema() {
        jdbcTemplate.update(DDL_QUERY);
    }

}
