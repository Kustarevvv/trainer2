package ru.akhitev.storage;

import org.springframework.stereotype.Repository;
import ru.akhitev.domain.model.Task;
import ru.akhitev.domain.repo.TaskRepository;

import java.util.*;

@Repository
public class TaskInMemoryStorage implements TaskRepository {
  private final Map<String, Task> tasks;

  public TaskInMemoryStorage() {
    tasks = new HashMap<>();
  }

  @Override
  public List<Task> findAll() {
    return tasks.values().stream().toList();
  }

  @Override
  public Optional<Task> findByCode(String code) {
    Task foundTask = tasks.get(code);
    if (Objects.nonNull(foundTask)) {
      return Optional.of(foundTask);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void add(Task task) {
    tasks.put(task.getCode(), task);
  }

  @Override
  public void update(Task task) {
    tasks.put(task.getCode(), task);
  }

  @Override
  public void remove(String code) {
    tasks.remove(code);
  }
}
