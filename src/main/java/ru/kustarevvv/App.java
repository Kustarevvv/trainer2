package ru.kustarevvv;

import ru.kustarevvv.domain.model.OpenQuestionCard;


public class App 
{
    public static void main( String[] args )
    {
        OpenQuestionCard question_1 = new OpenQuestionCard("Конструктор класса в Java это?", "Cпециальный блок кода, похожий на метод, предназначенный для инициализации полей объекта при его создании");
        System.out.println(question_1);
    }
}
