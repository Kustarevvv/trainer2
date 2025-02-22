package ru.akhitev.dao;

import org.springframework.stereotype.Repository;
import ru.akhitev.domain.model.Task;
import ru.akhitev.domain.repo.TaskRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskJdbcDao implements TaskRepository {
  private static final String DDL_QUERY = """
          CREATE TABLE tasks (ID int AUTO_INCREMENT PRIMARY KEY, code VARCHAR(10), title VARCHAR(50))
          """;
  private static final String FIND_ALL_QUERY = """
          SELECT code, title FROM tasks
          """;
  private static final String FIND_BY_CODE_QUERY = """
          SELECT code, title FROM tasks WHERE code = ?
          """;
  private static final String INSERT_TASK_QUERY = """
          INSERT INTO tasks(code, title) VALUES (?, ?)
          """;
  private static final String UPDATE_TASK_QUERY = """
          UPDATE tasks SET code=?, title=?
          """;
  private static final String DELETE_TASK_QUERY = """
          DELETE FROM tasks WHERE code=?
          """;
  private final DataSource dataSource;

  public TaskJdbcDao(DataSource dataSource) {
    this.dataSource = dataSource;
    initDataBase();
  }

  public void initDataBase() {
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DDL_QUERY)) {
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Task> findAll() {
    List<Task> tasks = new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
         ResultSet resultSet = statement.executeQuery();) {
      while (resultSet.next()) {
        Task task = new Task(resultSet.getString("code"),
                resultSet.getString("title"));
        tasks.add(task);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tasks;
  }

  @Override
  public Optional<Task> findByCode(String code) {
    List<Task> tasks = new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(FIND_BY_CODE_QUERY);) {
      statement.setString(1, code);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task(resultSet.getString("code"),
                resultSet.getString("title"));
        tasks.add(task);
      }
      resultSet.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tasks.isEmpty() ? Optional.empty() : Optional.of(tasks.get(0));
  }

  @Override
  public void add(Task task) {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_TASK_QUERY);){
      statement.setString(1, task.getCode());
      statement.setString(2, task.getTitle());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Task task) {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_QUERY);){
      statement.setString(1, task.getCode());
      statement.setString(2, task.getTitle());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void remove(String code) {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_TASK_QUERY);){
      statement.setString(1, code);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
