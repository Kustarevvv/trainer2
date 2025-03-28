package ru.kustarevvv.web.gui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;

@OpenAPIDefinition(info = @Info(title = "API приложения Question Manager", version = "v1.0.0"),
        servers = { @Server(url = "http://localhost:8080/") })
@Configuration
public class OpenApiConf {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
