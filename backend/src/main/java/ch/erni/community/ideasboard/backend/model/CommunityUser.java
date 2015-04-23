package ch.erni.community.ideasboard.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityUser {

    private String id;

	@Size(min = 5)
	private String username;

    private String phone;

    @Email
    @NotEmpty
    private String email;

	@Size(min = 5)
	private String password;

}
