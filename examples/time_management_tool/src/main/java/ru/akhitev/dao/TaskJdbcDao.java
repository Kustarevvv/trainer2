package ru.akhitev.dao;

import org.springframework.stereotype.Repository;
import ru.akhitev.domain.model.Task;
import ru.akhitev.domain.repo.TaskRepository;

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

  static {
    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public TaskJdbcDao() {
    initDataBase();
  }

  public void initDataBase() {
    Connection connection = null;
    try {
      connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(DDL_QUERY);
      statement.execute();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection);
    }
  }

  @Override
  public List<Task> findAll() {
    Connection connection = null;
    List<Task> tasks = new ArrayList<>();
    try {
      connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task(resultSet.getString("code"),
                resultSet.getString("title"));
        tasks.add(task);
      }
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection);
    }
    return tasks;
  }

  @Override
  public Optional<Task> findByCode(String code) {
    Connection connection = null;
    List<Task> tasks = new ArrayList<>();
    try {
      connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_BY_CODE_QUERY);
      statement.setString(1, code);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Task task = new Task(resultSet.getString("code"),
                resultSet.getString("title"));
        tasks.add(task);
      }
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection);
    }
    return tasks.isEmpty() ? Optional.empty() : Optional.of(tasks.get(0));
  }

  @Override
  public void add(Task task) {
    Connection connection = null;
    try {
      connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(INSERT_TASK_QUERY);
      statement.setString(1, task.getCode());
      statement.setString(2, task.getTitle());
      statement.execute();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection);
    }
  }

  @Override
  public void update(Task task) {
    Connection connection = null;
    try {
      connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_QUERY);
      statement.setString(1, task.getCode());
      statement.setString(2, task.getTitle());
      statement.execute();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection);
    }
  }

  @Override
  public void remove(String code) {
    Connection connection = null;
    try {
      connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(DELETE_TASK_QUERY);
      statement.setString(1, code);
      statement.execute();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection);
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
