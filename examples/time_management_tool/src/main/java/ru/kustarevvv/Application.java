package ru.kustarevvv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kustarevvv.config.SpringConfig;
import ru.kustarevvv.controller.ConsoleController;

public class Application {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ConsoleController controller = context.getBean(ConsoleController.class);
    controller.interactWithUser();
  }
}
