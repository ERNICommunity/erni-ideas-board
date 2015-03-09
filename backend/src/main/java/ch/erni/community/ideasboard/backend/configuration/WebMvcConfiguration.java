package ch.erni.community.ideasboard.backend.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author rap
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"ch.erni.community.ideasboard.backend.configuration", "ch.erni.community.ideasboard.backend"})
public class WebMvcConfiguration {
}
