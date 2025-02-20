package ru.akhitev.controller;

import org.springframework.stereotype.Component;
import ru.akhitev.domain.model.Task;
import ru.akhitev.domain.service.TaskService;

import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleController {
  private static final String MENU = """
          Введите [1], чтобы показать все задачи
          Введите [2], чтобы добавить задачу
          Введите [3], чтобы удалить задачу
          Введите [4], чтобы найти задачу
          Введите [5], чтобы выйти
          """;
  private final TaskService service;
  private final Scanner scanner;

  public ConsoleController(TaskService service) {
    this.service = service;
    scanner = new Scanner(System.in);
  }

  public void interactWithUser() {
    while(true) {
      printMenu();
      String operationCode = scanner.nextLine();
      switch (operationCode) {
        case "1" -> printAllTasks();
        case "2" -> addTask();
        case "3" -> removeTask();
        case "4" -> findTask();
        case "5" -> { return; }
        default -> System.out.println("Неизвестная команда");
      }
    }
  }

  private void printMenu() {
    System.out.println(MENU);
  }

  private void printAllTasks() {
    System.out.println(service.getAll());
  }

  private void addTask() {
    System.out.println("Введите код задачи");
    String code = scanner.nextLine();
    System.out.println("Введите название задачи");
    String title = scanner.nextLine();
    Task task = new Task(code, title);
    service.save(task);
  }

  private void removeTask() {
    System.out.println("Введите код задачи, которую хотите удалить");
    String code = scanner.nextLine();
    Optional<Task> task = service.getByCode(code);
    if (task.isPresent()) {
      System.out.println("Введите [Y], если точно хотите удалить задачу " + task.get());
      String confirmation = scanner.nextLine();
      if ("Y".equals(confirmation)) {
        service.delete(task.get());
      }
    } else {
      System.out.println("Такой задачи найти не удалось");
    }
  }

  private void findTask() {
    System.out.println("Введите код задачи, которую хотите найти");
    String code = scanner.nextLine();
    Optional<Task> task = service.getByCode(code);
    if (task.isPresent()) {
      System.out.println(task.get());
    } else {
      System.out.println("Такой задачи найти не удалось");
    }
  }
}
