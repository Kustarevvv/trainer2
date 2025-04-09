package ru.kustarevvv.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import ru.kustarevvv.console.controller.ConsoleController;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;
import ru.kustarevvv.domain.service.QuestionService;



@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private ConsoleController controller;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        controller.interactWithUser();
    }
}
