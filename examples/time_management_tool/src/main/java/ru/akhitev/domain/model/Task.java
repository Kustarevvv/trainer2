package ru.akhitev.domain.model;

import ru.akhitev.domain.utility.ValidationUtil;

import java.time.ZonedDateTime;

public class Task {
  private final String code;
  private String title;
  private String description;
  private ZonedDateTime deadLine;

  public Task(String code, String title) {
    ValidationUtil.validateNotEmpty(code, "code не может быть пустым");
    ValidationUtil.validateNotEmpty(title, "title не может быть пустым");
    this.code = code;
    this.title = title;
  }

  public String getCode() {
    return code;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    ValidationUtil.validateNotEmpty(title, "title не может быть пустым");
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ZonedDateTime getDeadLine() {
    return deadLine;
  }

  public void setDeadLine(ZonedDateTime deadLine) {
    if (ZonedDateTime.now().isAfter(deadLine)) {
      throw new IllegalArgumentException("deadLine не может быть в прошлом");
    }
    this.deadLine = deadLine;
  }

  @Override
  public String toString() {
    return "Task{" +
            "code='" + code + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", deadLine=" + deadLine +
            '}';
  }
}
