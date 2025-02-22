# Задание 3.1

В соответствии со слоеной архитектурой, добавить:

- в слой бизнес-логики
  
  - репозиторий
  
  - сервис

- в слой данных
  
  - реализацию репозитории

- в слой представления
  
  - контроллер для взаимодействия с пользователем

- в Application - соеденить все вместе и осуществить запуск

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
    
    package "controller" {
        class ConsoleController {
            - service: QuestionService
            + interactWithUser()
        }
    }
    
    QuestionRepository --> OpenQuestionCard
    QuestionInMemoryStorage ..|> QuestionRepository
    QuestionService --> QuestionRepository
    ConsoleController --> QuestionService
    Application --> ConsoleController
    Application --> QuestionService
    Application --> QuestionInMemoryStorage
}
@enduml
```

- В классе-моделе появилось поле `id` типа `Long`, являющийся уникальным идентификатором в системе

## Критерии приема

- Классы реализованы в соответствии со схемой
- Компоненты связываются через контекст Spring
- Не нарушаются прицнипы SOLID, KISS, прочие
- Изменения должны быть закоммичены в ветку `hw1`
- Если еще не сделан Pull Request из `hw1` в `master`, сделать
- Прислать ссылку на Pull Request
- После получения Approve-а задание будет считаться выполненным