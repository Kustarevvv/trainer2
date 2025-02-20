package ru.akhitev;

import ru.akhitev.controller.ConsoleController;
import ru.akhitev.domain.repo.TaskRepository;
import ru.akhitev.domain.service.TaskService;
import ru.akhitev.storage.TaskInMemoryStorage;

public class Application {
  public static void main(String[] args) {
    TaskRepository repository = new TaskInMemoryStorage();
    TaskService service = new TaskService(repository);
    ConsoleController controller = new ConsoleController(service);
    controller.interactWithUser();
  }
}
