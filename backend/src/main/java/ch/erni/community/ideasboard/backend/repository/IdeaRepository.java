package ch.erni.community.ideasboard.backend.repository;

import ch.erni.community.ideasboard.backend.model.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author rap
 */
public interface IdeaRepository extends MongoRepository<Idea, String> {
}
