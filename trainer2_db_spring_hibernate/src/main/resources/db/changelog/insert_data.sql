-- liquibase formatted sql
-- changeset developer:insert-test-data logicalFilePath:insert_data.sql
--comment: Добавление тестовых данных в таблицы
INSERT INTO project (id, question, expectedAnswer) VALUES
(1, 'Что такое Spring Framework?', 'Фреймворк для создания Java-приложений'),

INSERT INTO question (id, question, expectedAnswer, project_id) VALUES
(1, 'Какие основные модули Spring?', 'Core, Context, Beans, AOP, Data и другие', 1),
--rollback DELETE FROM question WHERE id IN (1, 2, 3, 4, 5);
--rollback DELETE FROM project WHERE id IN (1, 2, 3);