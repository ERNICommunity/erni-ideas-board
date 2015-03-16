package ch.erni.community.ideasboard.backend.controller;

import ch.erni.community.ideasboard.backend.Application;
import ch.erni.community.ideasboard.backend.model.Idea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class IdeaControllerTest {

    @Test
    public void testCreate() throws Exception {
        Idea idea = mock(Idea.class);


    }
}