package ch.erni.community.ideasboard.backend.controller;

import ch.erni.community.ideasboard.backend.Application;
import ch.erni.community.ideasboard.backend.configuration.MongoDbConfiguration;
import ch.erni.community.ideasboard.backend.model.Idea;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class, MongoDbConfiguration.class})
public class IdeaControllerTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCreate() throws Exception {
        Idea idea = mockIdea("Test", "Test Description");

        Gson gson = new Gson();
        String content = gson.toJson(idea);

        this.mockMvc.perform(post("/ideas")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @Rollback
    public void testListIssues() throws Exception {

        List<Idea> ideas = Arrays.asList(
                mockIdea("Idea1", "Ideas Description1"),
                mockIdea("Idea2", "Ideas Description2"),
                mockIdea("Idea3", "Ideas Description3")
        );

        Gson gson = new Gson();
        for (Idea idea : ideas) {
            this.mockMvc.perform(post("/ideas").content(gson.toJson(idea)).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }

        String json = this.mockMvc.perform(get("/ideas").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<Idea> result = gson.fromJson(json, List.class);
        int i = 0;
        /*this.mockMvc.perform(get("/ideas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());*/
    }



    private Idea mockIdea(String name, String description) {
        return Idea.builder().name(name).description(description).build();
    }
}