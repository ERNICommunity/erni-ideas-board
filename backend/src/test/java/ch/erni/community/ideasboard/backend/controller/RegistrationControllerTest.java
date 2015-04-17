package ch.erni.community.ideasboard.backend.controller;

import ch.erni.community.ideasboard.backend.Application;
import ch.erni.community.ideasboard.backend.configuration.MongoDbConfiguration;
import ch.erni.community.ideasboard.backend.model.CommunityUser;
import ch.erni.community.ideasboard.backend.util.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author rap
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class, MongoDbConfiguration.class})
public class RegistrationControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test(expected = MethodArgumentNotValidException.class)
	public void invalidUser() throws Exception {
		CommunityUser communityUser = CommunityUser.builder().email("bad-email").build();

		String communityUserJson = new JsonUtils().jsonFromUser(communityUser);

		MvcResult response = this.mockMvc.perform(post("/register")
				.content(communityUserJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andReturn();

		throw response.getResolvedException();
	}

}