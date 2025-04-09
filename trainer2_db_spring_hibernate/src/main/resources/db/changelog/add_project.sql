-- liquibase formatted sql
-- changeset developer:add-project logicalFilePath:-
--comment: Создание таблицы project
CREATE TABLE project (id int AUTO_INCREMENT PRIMARY KEY, question VARCHAR(100), expectedAnswer VARCHAR(100));
comment on table question is 'Таблица с вопросами';
comment on column question.id is 'ID вопроса';
comment on column question.question is 'Вопрос';
comment on column question.expectedAnswer is 'Ответ';
ALTER TABLE question ADD COLUMN project_id int;
--rollback DROP TABLE project;
--rollback ALTER TABLE question DROP COLUMN project_id;