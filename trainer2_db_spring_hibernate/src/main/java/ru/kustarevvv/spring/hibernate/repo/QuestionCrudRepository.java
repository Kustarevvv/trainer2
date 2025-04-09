package ru.kustarevvv.spring.hibernate.repo;
import org.springframework.data.repository.CrudRepository;
import ru.kustarevvv.spring.hibernate.entity.OpenQuestionCardEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionCrudRepository extends CrudRepository<OpenQuestionCardEntity, Long>{
    List<OpenQuestionCardEntity> findAll();
    Optional<OpenQuestionCardEntity> findById(Long id);
}
