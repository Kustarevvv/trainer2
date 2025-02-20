package ru.akhitev.domain.repo;

import ru.akhitev.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
  List<Task> findAll();
  Optional<Task> findByCode(String code);
  void add(Task task);
  void update(Task task);
  void remove(String code);
}
