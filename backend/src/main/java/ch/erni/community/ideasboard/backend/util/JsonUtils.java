package ch.erni.community.ideasboard.backend.util;

import ch.erni.community.ideasboard.backend.model.CommunityUser;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Component
public class JsonUtils {

    private Logger logger = Logger.getLogger(JsonUtils.class.getName());

    public Optional<CommunityUser> userFromJson(String communityUserJson) {
        Optional<CommunityUser> communityUser = Optional.empty();
        try {
            communityUser = Optional.ofNullable(new Gson().fromJson(communityUserJson, CommunityUser.class));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return communityUser;
    }

    public String jsonFromUser(CommunityUser communityUser) {
        try {
            return new Gson().toJson(communityUser);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return "{}";
    }

}
