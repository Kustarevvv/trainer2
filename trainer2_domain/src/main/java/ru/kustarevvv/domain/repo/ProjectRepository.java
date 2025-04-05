package ru.kustarevvv.domain.repo;

import ru.kustarevvv.domain.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<Project> findAll();
    Optional<Project> findById(Long id);
    void add(Project project);
    void update(Project project);
    void remove(Long id);
}
