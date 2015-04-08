package ch.erni.community.ideasboard.backend.repository;

import ch.erni.community.ideasboard.backend.model.IdeasBoardUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public interface IdeasBoardUserRepository extends MongoRepository<IdeasBoardUser, String> {

    IdeasBoardUser findOneByEmail(String email);

}
