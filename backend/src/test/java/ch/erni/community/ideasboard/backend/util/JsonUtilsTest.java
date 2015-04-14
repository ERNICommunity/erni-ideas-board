package ch.erni.community.ideasboard.backend.util;

import ch.erni.community.ideasboard.backend.model.CommunityUser;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class JsonUtilsTest {

    private static final String JSON_USER = "{" +
            "\"username\":\"test\"," +
            "\"phone\":\"12345\"," +
            "\"email\":\"email@test.com\"" +
            "}";

    private static final CommunityUser COMMUNITY_USER = CommunityUser.builder().email("email@test.com").phone("12345").username("test").build();

    private JsonUtils jsonUtils;

    @Before
    public void before() {
        jsonUtils = new JsonUtils();
    }

    @Test
    public void testUserFromJson() throws Exception {
        Optional<CommunityUser> communityUser = jsonUtils.userFromJson(JSON_USER);

        assertNotNull(communityUser);
        assertNotNull(communityUser.get());
        assertEquals(COMMUNITY_USER, communityUser.get());
    }

    @Test
    public void testJsonFromUser() throws Exception {
        String json = jsonUtils.jsonFromUser(COMMUNITY_USER);

        assertNotNull(json);
        assertEquals(JSON_USER, json);
    }
}