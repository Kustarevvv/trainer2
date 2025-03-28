package ru.kustarevvv.web.gui.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.kustarevvv.spring.hibernate.config.DbConfig;
@Configuration
@Import(DbConfig.class)
@ComponentScan(basePackages = "ru.kustarevvv")
@PropertySource("jdbc.properties")
public class SpringConfig {

}