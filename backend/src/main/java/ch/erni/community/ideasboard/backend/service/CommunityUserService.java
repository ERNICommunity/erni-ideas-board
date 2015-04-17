package ch.erni.community.ideasboard.backend.service;

import ch.erni.community.ideasboard.backend.model.CommunityUser;
import ch.erni.community.ideasboard.backend.model.IdeasBoardUser;
import ch.erni.community.ideasboard.backend.repository.IdeasBoardUserRepository;
import ch.erni.community.ideasboard.backend.security.CommunityAuthentication;
import ch.erni.community.ideasboard.backend.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Service
public class CommunityUserService {

    private static final String ERNI_MOODS_USER_REPOSITORY = "http://moodyrest.azurewebsites.net/users";

	@Autowired
    JsonUtils jsonUtils;

    @Autowired
    IdeasBoardUserRepository ideasBoardUserRepository;

    public boolean register(CommunityUser communityUser) {
        ResponseEntity<String> jsonResponse = postCommunityUser(communityUser);

        ideasBoardUserRepository.save(IdeasBoardUser.builder().email(communityUser.getEmail()).build());

        return jsonResponse.getStatusCode().is2xxSuccessful();
    }

    ResponseEntity<String> postCommunityUser(CommunityUser communityUser) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForEntity(ERNI_MOODS_USER_REPOSITORY, communityUser, String.class);
    }

    public CommunityAuthentication authenticate(String username, String password) {
        ResponseEntity<String> jsonResponse = authenticateCommunityUser(username, password);

        Optional<CommunityUser> communityUser = jsonUtils.userFromJson(jsonResponse.getBody());

        IdeasBoardUser ideasBoardUser = ideasBoardUserRepository.findOneByEmail(communityUser.get().getEmail());

        return new CommunityAuthentication(jsonResponse.getStatusCode().is2xxSuccessful(), ideasBoardUser);
    }

    ResponseEntity<String> authenticateCommunityUser(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForEntity(ERNI_MOODS_USER_REPOSITORY + "/" + username + "/" + password, String.class);
    }

}
