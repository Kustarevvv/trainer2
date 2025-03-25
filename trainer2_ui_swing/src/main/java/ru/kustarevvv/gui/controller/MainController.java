package ru.kustarevvv.gui.controller;

import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.domain.service.QuestionService;
import ru.kustarevvv.gui.model.QuestionTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class MainController implements Runnable {
    private final QuestionService service;
    private JFrame mainFrame;
    private JPanel mainPanel;

    public MainController(QuestionService service) {
        this.service = service;
    }

    @Override
    public void run() {
        mainFrame = new JFrame("Open Question Tool");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setJMenuBar(prepareMenu());
        prepareMainPanelForListQuestion();
        mainFrame.setVisible(true);
    }

    private void prepareMainPanelForAddQuestion(Optional<OpenQuestionCard> questionForEdit) {
        if (mainPanel != null) {
            mainFrame.remove(mainPanel);
        }
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints layoutConstraints = new GridBagConstraints();

//        layoutConstraints.gridx = 0;
//        layoutConstraints.gridy = 0;
//        mainPanel.add(new JLabel("ID"), layoutConstraints);
//        JTextField idField = new JTextField(15);
//        layoutConstraints = new GridBagConstraints();
//        layoutConstraints.gridx = 1;
//        layoutConstraints.gridy = 0;
//        mainPanel.add(idField, layoutConstraints);

        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        mainPanel.add(new JLabel("Вопрос"), layoutConstraints);
        JTextField questionField = new JTextField(15);
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        mainPanel.add(questionField, layoutConstraints);

        //layoutConstraints = new GridBagConstraints();

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        mainPanel.add(new JLabel("Ответ"), layoutConstraints);
        JTextField expectedAnswerField = new JTextField(15);
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 2;
        mainPanel.add(expectedAnswerField, layoutConstraints);

        JButton addButton = new JButton("Добавить");
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 3;
        layoutConstraints.gridwidth = 2;

        questionForEdit.ifPresent( t -> {
            //idField.setText(String.valueOf(t.getId()));
            questionField.setText(t.getQuestion());
            expectedAnswerField.setText(t.getExpectedAnswer());
        });

        addButton.addActionListener(event -> {
            //проверяет есть ли уже заданный id, чтобы не создавались дубликаты
            Long id = questionForEdit.map(OpenQuestionCard::getId).orElse(null);

            OpenQuestionCard openQuestionCard = new OpenQuestionCard(
                    id,
                    questionField.getText(),
                    expectedAnswerField.getText()
            );

            service.save(openQuestionCard);
            prepareMainPanelForListQuestion();
            //заменил
        });
        mainPanel.add(addButton, layoutConstraints);
        mainFrame.add(mainPanel);
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }

    private void prepareMainPanelForListQuestion() {
        if (mainPanel != null) {
            mainFrame.remove(mainPanel);
        }
        mainPanel = new JPanel(new BorderLayout());
        JTable table = new JTable(new QuestionTableModel(service.getAll()));
        mainPanel.add(table, BorderLayout.CENTER);
        mainPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        mainFrame.add(mainPanel);
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }

    private JMenuBar prepareMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu questionMenu = new JMenu("Question");
        JMenuItem addQuestion = new JMenuItem("Добавить вопрос");
        addQuestion.addActionListener((event) -> {
            prepareMainPanelForAddQuestion(Optional.empty());
        });
        questionMenu.add(addQuestion);
        menuBar.add(questionMenu);
        JMenuItem listQuestion = new JMenuItem("Просмтреть вопросы");
        listQuestion.addActionListener((event) -> {
            prepareMainPanelForListQuestion();
        });
        questionMenu.add(listQuestion);
        JMenuItem removeQuestion = new JMenuItem("Удалить вопрос");
        removeQuestion.addActionListener((event) -> {
            OpenQuestionCard questionToDelete = (OpenQuestionCard) JOptionPane.showInputDialog(
                    mainFrame,
                    "Какой вопрос хотите удалить?",
                    "Удаление вопроса",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    service.getAll().toArray(),
                    null);
            service.delete(questionToDelete);
            prepareMainPanelForListQuestion();
        });
        questionMenu.add(removeQuestion);
        JMenuItem editQuestion = new JMenuItem("Обновить вопрос");
        editQuestion.addActionListener((event) -> {
            OpenQuestionCard questionToDelete = (OpenQuestionCard) JOptionPane.showInputDialog(
                    mainFrame,
                    "Какой вопрос хотите изменить?",
                    "Изменение вопроса",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    service.getAll().toArray(),
                    null);
            prepareMainPanelForAddQuestion(Optional.of(questionToDelete));
        });
        questionMenu.add(editQuestion);
        menuBar.add(questionMenu);
        return menuBar;
    }
}