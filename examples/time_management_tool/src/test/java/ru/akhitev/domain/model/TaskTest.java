package ru.akhitev.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.ZonedDateTime;

public class TaskTest {
  private static final String TASK_CODE = "T101";
  private static final String TASK_TITLE = "Сделать презентацию для 3 урока";
  private Task task;

  @BeforeEach
  void setUp() {
    task = new Task(TASK_CODE, TASK_TITLE);
  }

  @Test
  @DisplayName("Создание Task с корректными code и title проходит успешно")
  void having_correctTitle_when_newTask_then_created() {
    String title = "Сделать презентацию для 3 урока";
    Task task = new Task(TASK_CODE, title);
    Assertions.assertEquals(title, task.getTitle());
  }

  @Test
  @DisplayName("Создание Task с code равным null выбрасывает исключение")
  void having_nullCode_when_newTask_then_exceptionThrown() {
    Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new Task(null, TASK_TITLE));
  }

  @Test
  @DisplayName("Создание Task с code равным null выбрасывает исключение")
  void having_emptyCode_when_newTask_then_exceptionThrown() {
    Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new Task("", TASK_TITLE));
  }

  @Test
  @DisplayName("Создание Task с title равным null выбрасывает исключение")
  void having_nullTitle_when_newTask_then_exceptionThrown() {
    Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new Task(TASK_CODE, null));
  }

  @Test
  @DisplayName("Создание Task с title равным null выбрасывает исключение")
  void having_emptyTitle_when_newTask_then_exceptionThrown() {
    Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new Task(TASK_CODE, ""));
  }

  @Test
  @DisplayName("Установка правильного deadLine-а проходит успешно")
  void having_correctDate_when_newSetDeadLine_then_created() {
    ZonedDateTime deadLine = ZonedDateTime.now().plusDays(1);
    task.setDeadLine(deadLine);
    Assertions.assertEquals(deadLine, task.getDeadLine());
  }

  @Test
  @DisplayName("Установка deadLine-а в прошлом заканчивается исключением")
  void having_dateInThePast_when_newSetDeadLine_then_created() {
    ZonedDateTime deadLine = ZonedDateTime.now().minusDays(1);
    Assertions.assertThrowsExactly(IllegalArgumentException.class, () ->  task.setDeadLine(deadLine));
  }

  @Test
  @DisplayName("Создание Task с корректным title проходит успешно")
  void having_correctTitle_when_setTitle_then_created() {
    String title = "Сделать презентацию для 4 урока";
    task.setTitle(title);
    Assertions.assertEquals(title, task.getTitle());
  }

  @Test
  @DisplayName("Создание Task с title равным null выбрасывает исключение")
  void having_nullTitle_when_setTitle_then_exceptionThrown() {
    Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> task.setTitle(null));
  }

  @Test
  @DisplayName("Создание Task с title равным null выбрасывает исключение")
  void having_emptyTitle_when_setTitle_then_exceptionThrown() {
    Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> task.setTitle(""));
  }
}
