package ch.erni.community.ideasboard.backend.service;

import ch.erni.community.ideasboard.backend.Application;
import ch.erni.community.ideasboard.backend.EmbeddedMongoDbTest;
import ch.erni.community.ideasboard.backend.configuration.MongoDbConfiguration;
import ch.erni.community.ideasboard.backend.model.CommunityUser;
import ch.erni.community.ideasboard.backend.repository.IdeasBoardUserRepository;
import ch.erni.community.ideasboard.backend.security.CommunityAuthentication;
import ch.erni.community.ideasboard.backend.util.JsonUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, MongoDbConfiguration.class})
public class CommunityUserServiceTest extends EmbeddedMongoDbTest {

    private CommunityUserService communityUserService;

    private CommunityUser communityUser;

    @Autowired
    private JsonUtils jsonUtils;

    @Autowired
    private IdeasBoardUserRepository ideasBoardUserRepository;

    @BeforeClass
    public static void beforeClass() throws IOException {
        initializeDB();
    }

    @AfterClass
    public static void afterClass() throws InterruptedException {
        shutdownDB();
    }

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
    }
}