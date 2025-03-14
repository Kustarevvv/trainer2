package ru.kustarevvv.controller;

import org.springframework.stereotype.Component;

import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.service.QuestionService;

import java.net.SocketOption;
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
    private final QuestionService service;
    private final Scanner scanner;
    public ConsoleController(QuestionService service) {
        this.service = service;
        scanner = new Scanner(System.in);
    }
    public void interactWithUser() {
        while(true) {
            printMenu();
            String operationCode = scanner.nextLine();
            switch (operationCode) {
                case "1" -> printAllOpenQuestionCards();
                case "2" -> addOpenQuestionCard();
                case "3" -> removeOpenQuestionCard();
                case "4" -> findOpenQuestionCard();
                case "5" -> { return; }
                default -> System.out.println("Неизвестная команда");
            }
        }
    }
    private void printMenu() {
        System.out.println(MENU);
    }
    private void printAllOpenQuestionCards() {
        System.out.println(service.getAll());
    }
    private void addOpenQuestionCard() {
        System.out.println("Введите id открытого вопроса");
        Long id = Long.valueOf(scanner.nextLine());
        System.out.println("Введите открытый вопрос");
        String question = scanner.nextLine();
        System.out.println("Введите ответ на открытый вопрос");
        String expectedAnswer = scanner.nextLine();
        OpenQuestionCard openQuestionCard = new OpenQuestionCard(id, question, expectedAnswer);
        service.save(openQuestionCard);
    }
    private void removeOpenQuestionCard() {
        System.out.println("Введите id открытого вопроса, который хотите удалить");
        Long id = Long.valueOf(scanner.nextLine());
        Optional<OpenQuestionCard> openQuestionCard = service.getById(id);
        if (openQuestionCard.isPresent()) {
            System.out.println("Введите [Y], если точно хотите удалить открытый вопрос " + openQuestionCard.get());
            String confirmation = scanner.nextLine();
            if ("Y".equals(confirmation)) {
                service.delete(openQuestionCard.get());
            }
        } else {
            System.out.println("Не удалось найти такого открытого вопроса");
        }
    }

    private void findOpenQuestionCard() {
        System.out.println("Введите id вопроса, который хотите найти");
        Long id = Long.valueOf(scanner.nextLine());
        Optional<OpenQuestionCard> openQuestionCard = service.getById(id);
        if (openQuestionCard.isPresent()) {
            System.out.println(openQuestionCard.get());
        } else {
            System.out.println("Такого открытого вопроса не существует");
        }
    }
    }


