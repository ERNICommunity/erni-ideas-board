package ch.erni.community.ideasboard.backend.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Document
@Data
@Builder
public class IdeasBoardUser {

    @Id
    private String id;

    @Email
    private String email;

}
