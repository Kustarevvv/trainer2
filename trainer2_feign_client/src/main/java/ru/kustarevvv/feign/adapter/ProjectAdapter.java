package ru.kustarevvv.feign.adapter;

import ru.kustarevvv.domain.model.Project;
import ru.kustarevvv.domain.repo.ProjectRepository;
import ru.kustarevvv.feign.client.ProjectFeignClient;
import ru.kustarevvv.web.api.dto.ProjectDto;
import ru.kustarevvv.web.api.mapper.QuestionDtoMapper;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectAdapter implements ProjectRepository {
    private final ProjectFeignClient feignClient;
    private final QuestionDtoMapper mapper;

    public ProjectAdapter(ProjectFeignClient feignClient, QuestionDtoMapper mapper) {
        this.feignClient = feignClient;
        this.mapper = mapper;
    }

    @Override
    public List<Project> findAll() {
        List<ProjectDto> projects = feignClient.list();
        return projects.stream()
                .map(dto -> mapper.mapToModel(dto))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Project> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void add(Project project) {
        feignClient.add(mapper.mapToDto(project));
    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void remove(Long id) {
        feignClient.remove(id);
    }
}
