package ru.kustarevvv.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kustarevvv.domain.service.QuestionService;
import ru.kustarevvv.gui.config.SpringConfig;
import ru.kustarevvv.gui.controller.MainController;

import javax.swing.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Main.class)
                .headless(false).run(args);
        logger.info("Начало работы приложения");
        QuestionService questionService = ctx.getBean(QuestionService.class);
        SwingUtilities.invokeLater(new MainController(questionService));
        logger.info("Конец работы приложения");
    }
}