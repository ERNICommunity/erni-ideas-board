package ch.erni.community.ideasboard.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author rap
 */
@Configuration
@EnableMongoRepositories(basePackages = "ch.erni.community.ideasboard.backend.repository")
@EnableMongoAuditing
public class MongoDbConfiguration {



}
