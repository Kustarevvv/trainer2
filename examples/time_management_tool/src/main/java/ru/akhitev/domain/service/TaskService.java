package ru.akhitev.domain.service;

import ru.akhitev.domain.model.Task;
import ru.akhitev.domain.repo.TaskRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TaskService {
  private final TaskRepository repository;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  public List<Task> getAll() {
    return repository.findAll();
  }

  public Optional<Task> getByCode(String code) {
    if (Objects.isNull(code)) {
      return Optional.empty();
    }
    return repository.findByCode(code);
  }

  public boolean contains(Task task) {
    if (isTaskInvalid(task)) {
      return false;
    }
    return repository.findByCode(task.getCode()).isPresent();
  }

  public void save(Task task) {
    if (isTaskInvalid(task)) {
      return;
    }
    if (contains(task)) {
      repository.update(task);
    } else {
      repository.add(task);
    }
  }

  public void delete(Task task) {
    if (isTaskInvalid(task)) {
      return;
    }
    repository.remove(task.getCode());
  }

  private boolean isTaskInvalid(Task task) {
    return Objects.isNull(task) || Objects.isNull(task.getCode());
  }
}
