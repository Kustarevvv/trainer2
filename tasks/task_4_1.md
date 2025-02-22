# Задание 4.1

Добавить реализацию репозитория на базе JDBC и настроить DataSource в контексте Spring

## Техническое описание

### Архитектура

#### Диаграмма классов

```plantuml
@startuml

package "ru.<ваш_ник>" {
    class Application {
      main()
    }

    package "domain" {
        package "model" {
          class OpenQuestionCard {
            - Long id
            - question: String
            - expectedAnswer
            + getQuestion(): String
            + checkAnswer(answer: String): boolean
          }
        }

        package "repo" {
            interface QuestionRepository {
                + findAll(): List<OpenQuestionCard>
                + findById(id: Long): Optional<OpenQuestionCard>
                + add(task: OpenQuestionCard)
                + update(task: OpenQuestionCard)
                + remove(id: Long)
            }
        }

        package "service" {
            class QuestionService {
                - repository: QuestionRepository
                + getAll(): List<OpenQuestionCard>
                + getById(id: Long): Optional<OpenQuestionCard>
                + contains(task: OpenQuestionCard): boolean
                + save(task: OpenQuestionCard)
                + delete(id: Long)
                - isTaskInvalid(task: OpenQuestionCard): boolean
            }
        }
    }

    package "storage" {
        class QuestionInMemoryStorage {
            + findAll(): List<OpenQuestionCard>
            + findById(id: Long): Optional<OpenQuestionCard>
            + add(task: OpenQuestionCard)
            + update(task: OpenQuestionCard)
            + remove(id: Long)
        }
    }

    package "dao" {
            class QuestionJdbcDao {
            + findAll(): List<OpenQuestionCard>
            + findById(id: Long): Optional<OpenQuestionCard>
            + add(task: OpenQuestionCard)
            + update(task: OpenQuestionCard)
            + remove(id: Long)
        }
    }

    package "controller" {
        class ConsoleController {
            - service: QuestionService
            + interactWithUser()
        }
    }

    QuestionRepository --> OpenQuestionCard
    QuestionInMemoryStorage ..|> QuestionRepository
    QuestionJdbcDao ..|> QuestionRepository
    QuestionService --> QuestionRepository
    ConsoleController --> QuestionService
    Application --> ConsoleController
    Application --> QuestionService
    Application --> QuestionJdbcDao
}
@enduml
```

## Критерии приема

- В контексте Spring добавлен DataSource
- Добавлена реализация репозитория на JDBC
- У реализации репозитория не нарушаются принципы SOLID, KISS и пр.
