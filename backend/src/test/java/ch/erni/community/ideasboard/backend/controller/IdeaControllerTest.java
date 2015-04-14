package ch.erni.community.ideasboard.backend.controller;

import ch.erni.community.ideasboard.backend.Application;
import ch.erni.community.ideasboard.backend.configuration.MongoDbConfiguration;
import ch.erni.community.ideasboard.backend.model.Idea;
import ch.erni.community.ideasboard.backend.model.Status;
import ch.erni.community.ideasboard.backend.repository.IdeaRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class, MongoDbConfiguration.class})
public class IdeaControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private IdeaRepository ideaRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    @Before
    public void after() {
        ideaRepository.deleteAll();
    }

    @Test
    public void testCreate() throws Exception {
        Idea idea = ideaStub();

        Gson gson = createGsonParser();
        String content = gson.toJson(idea);

        this.mockMvc.perform(post("/ideas")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListIssues() throws Exception {
        ideaRepository.save(ideaStub());

        String jsonResult = this.mockMvc.perform(get("/ideas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Idea> ideas = createGsonParser().fromJson(jsonResult, new TypeToken<List<Idea>>() {
        }.getType());

        assertEquals(1, ideas.size());

        Idea idea = ideas.get(0);

        assertNotNull(idea.getId());
        Idea stub = ideaStub();
        stub.setId(idea.getId());
        stub.setCreatedDate(idea.getCreatedDate());

        assertEquals(stub, idea);

        assertNotNull(idea.getCreatedDate());
    }

    private Idea ideaStub() {
        return Idea.builder()
                .author("test")
                .description("test description")
                .name("test name")
                .status(Status.DRAFT)
                .tags(Arrays.asList("test", "awesome"))
                .build();
    }

    private Gson createGsonParser() {
        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));

        return builder.create();
    }
}