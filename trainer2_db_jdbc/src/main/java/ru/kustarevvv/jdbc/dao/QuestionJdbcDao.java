package ru.kustarevvv.jdbc.dao;

import org.springframework.stereotype.Repository;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class QuestionJdbcDao implements QuestionRepository {

    private final DataSource dataSource;
    public QuestionJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
        initDataBase();
    }
    private static final String DDL_QUERY = """
          Create table openQuestionCards (id int primary key, question varchar(100), expectedAnswer varchar(100))
          """;
    private static final String INSERT_QUESTION_QUERY = """
          INSERT INTO openQuestionCards(question) VALUES (?)
          """;
    private static final String FIND_ALL_QUERY = """
          SELECT id, question, expectedAnswer FROM openQuestionCards
          """;
    private static final String FIND_BY_ID_QUERY = """
          SELECT id, question, expectedAnswer FROM openQuestionCards WHERE id = ?
          """;
    private static final String UPDATE_QUESTION_QUERY = """
          UPDATE openQuestionCards question=?, expectedAnswer =?
          """;
    private static final String DELETE_QUESTION_QUERY = """
          DELETE FROM openQuestionCards WHERE id=?
          """;

    public void initDataBase() {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DDL_QUERY);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void add(OpenQuestionCard openQuestionCard) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_QUESTION_QUERY);
            statement.setString(1, openQuestionCard.getQuestion());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(OpenQuestionCard openQuestionCard) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUESTION_QUERY);){
            statement.setString(1, openQuestionCard.getQuestion());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUESTION_QUERY);){
            statement.setString(1, String.valueOf(id));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCard> openQuestionCards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                OpenQuestionCard openQuestionCard = new OpenQuestionCard(Long.parseLong(resultSet.getString("id")),
                        resultSet.getString("question"), resultSet.getString("expectedAnswer"));
                openQuestionCards.add(openQuestionCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return openQuestionCards;
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        List<OpenQuestionCard> openQuestionCards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OpenQuestionCard openQuestionCard = new OpenQuestionCard(Long.parseLong(resultSet.getString("id")),
                        resultSet.getString("question"), resultSet.getString("expectedAnswer"));
                openQuestionCards.add(openQuestionCard);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return openQuestionCards.isEmpty() ? Optional.empty() : Optional.of(openQuestionCards.get(0));
    }

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:task;DB_CLOSE_DELAY=-1",
                "admin", "admin");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
