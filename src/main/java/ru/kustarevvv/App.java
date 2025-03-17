package ru.kustarevvv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kustarevvv.config.SpringConfig;
import ru.kustarevvv.controller.ConsoleController;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.repo.QuestionRepository;
import ru.kustarevvv.domain.service.QuestionService;
import ru.kustarevvv.storage.QuestionlnMemoryStorage;


public class App
{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ConsoleController controller = context.getBean(ConsoleController.class);
        controller.interactWithUser();
    }
}
