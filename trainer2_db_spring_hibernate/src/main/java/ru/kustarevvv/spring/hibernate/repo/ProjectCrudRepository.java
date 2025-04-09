package ru.kustarevvv.spring.hibernate.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kustarevvv.spring.hibernate.entity.ProjectEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectCrudRepository extends CrudRepository<ProjectEntity, Long> {
    List<ProjectEntity> findAll();
    Optional<ProjectEntity> findById(Long id);
}
