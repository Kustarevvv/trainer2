package ru.kustarevvv.gui.model;

import ru.kustarevvv.domain.model.OpenQuestionCard;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class QuestionTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[]{"ID", "Вопрос", "Ответ"};
    private final List<OpenQuestionCard> openQuestionCards;

    public QuestionTableModel(List<OpenQuestionCard> openQuestionCards) {
        this.openQuestionCards = openQuestionCards;
    }

    @Override
    public int getRowCount() {
        return openQuestionCards.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OpenQuestionCard card = openQuestionCards.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return card.getId();
            case 1:
                return card.getQuestion();
            case 2:
                return card.getExpectedAnswer();
            default:
                throw new IllegalArgumentException("Недопустимый индекс столбца: " + columnIndex);
        }
    }
}
