package ch.erni.community.ideasboard.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityUser {

    private String id;

    private String username;

    private String phone;

    @Email
    @NotEmpty
    private String email;

    private String password;

}
