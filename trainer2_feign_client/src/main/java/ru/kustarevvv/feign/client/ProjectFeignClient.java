package ru.kustarevvv.feign.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import ru.kustarevvv.web.api.dto.ProjectDto;

import java.util.List;

public interface ProjectFeignClient {
    @RequestLine("GET /project")
    @Headers({"Content-Type: application/json"})
    List<ProjectDto> list();
    @RequestLine("POST /project")
    @Headers({"Content-Type: application/json"})
    void add(ProjectDto dto);
    @RequestLine("PUT /project")
    @Headers({"Content-Type: application/json"})
    void update(ProjectDto dto);
    @RequestLine("DELETE /project/{id}")
    @Headers({"Content-Type: application/json"})
    List<ProjectDto> remove(@Param("id") Long id);
}
