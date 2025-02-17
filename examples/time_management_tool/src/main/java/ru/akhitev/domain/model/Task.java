package ru.akhitev.domain.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Task {
  private String title;
  private String description;
  private ZonedDateTime deadLine;

  public Task(String title) {
    if (Objects.isNull(title) || title.isEmpty()) {
      throw new IllegalArgumentException("title не может быть пустым");
    }
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (Objects.isNull(title) || title.isEmpty()) {
      throw new IllegalArgumentException("title не может быть пустым");
    }
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
}
