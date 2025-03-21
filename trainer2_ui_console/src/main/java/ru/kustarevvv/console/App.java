package ru.kustarevvv.console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kustarevvv.console.config.SpringConfig;
import ru.kustarevvv.console.controller.ConsoleController;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;
import ru.kustarevvv.domain.service.QuestionService;



public class App
{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ConsoleController controller = context.getBean(ConsoleController.class);
        controller.interactWithUser();
    }
}
