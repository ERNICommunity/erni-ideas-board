package ch.erni.community.ideasboard.backend.service;

import ch.erni.community.ideasboard.backend.Application;
import ch.erni.community.ideasboard.backend.configuration.MongoDbConfiguration;
import ch.erni.community.ideasboard.backend.model.CommunityUser;
import ch.erni.community.ideasboard.backend.model.IdeasBoardUser;
import ch.erni.community.ideasboard.backend.repository.IdeasBoardUserRepository;
import ch.erni.community.ideasboard.backend.security.CommunityAuthentication;
import ch.erni.community.ideasboard.backend.util.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, MongoDbConfiguration.class})
public class CommunityUserServiceTest {

    private CommunityUserService communityUserService;

    private CommunityUser communityUser;

    @Autowired
    private JsonUtils jsonUtils;

    @Autowired
    private IdeasBoardUserRepository ideasBoardUserRepository;

    @Before
    public void before() {
        communityUser = CommunityUser.builder()
                .email("test@erni.sk")
                .password("secret")
                .username("test")
                .password("+42191234567890")
                .build();

        communityUserService = spy(new CommunityUserService());
        communityUserService.ideasBoardUserRepository = ideasBoardUserRepository;
        communityUserService.jsonUtils = jsonUtils;

        doReturn(ResponseEntity.ok(jsonUtils.jsonFromUser(communityUser))).when(communityUserService).authenticateCommunityUser("test", "secret");
        doReturn(ResponseEntity.ok("{\"status\" : \"ok\"}")).when(communityUserService).postCommunityUser(communityUser);
    }

    @Test
    public void testRegister() throws Exception {
        boolean result = communityUserService.register(communityUser);

        assertTrue(result);
    }

    @Test
    public void testAuthenticate() throws Exception {
        CommunityAuthentication communityAuthentication = communityUserService.authenticate("test", "secret");

        assertTrue(communityAuthentication.isAuthenticated());
        assertEquals("test@erni.sk", communityAuthentication.getDetails());
        assertEquals(null, communityAuthentication.getCredentials());
        assertEquals("test@erni.sk", communityAuthentication.getName());

        Object principal = communityAuthentication.getPrincipal();

        assertTrue(principal instanceof IdeasBoardUser);

        IdeasBoardUser ideasBoardUser = (IdeasBoardUser) principal;

        assertNotNull(ideasBoardUser.getId());
        assertEquals(ideasBoardUser.getEmail(), communityUser.getEmail());
    }
}