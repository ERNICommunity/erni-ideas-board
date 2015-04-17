package ch.erni.community.ideasboard.backend.util;

import ch.erni.community.ideasboard.backend.model.CommunityUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Component
public class JsonUtils {

    private Logger logger = Logger.getLogger(JsonUtils.class.getName());

	public Gson createGsonParser() {
		// Creates the json object which will manage the information received
		GsonBuilder builder = new GsonBuilder();

		// Register an adapter to manage the date types as long values
		builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));

		return builder.create();
	}

    public Optional<CommunityUser> userFromJson(String communityUserJson) {
        Optional<CommunityUser> communityUser = Optional.empty();
        try {
            communityUser = Optional.ofNullable(createGsonParser().fromJson(communityUserJson, CommunityUser.class));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return communityUser;
    }

    public String jsonFromUser(CommunityUser communityUser) {
        try {
            return createGsonParser().toJson(communityUser);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return "{}";
    }

}
