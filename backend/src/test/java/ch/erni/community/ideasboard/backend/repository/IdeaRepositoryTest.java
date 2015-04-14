package ch.erni.community.ideasboard.backend.repository;

import ch.erni.community.ideasboard.backend.Application;
import ch.erni.community.ideasboard.backend.configuration.MongoDbConfiguration;
import ch.erni.community.ideasboard.backend.model.Idea;
import ch.erni.community.ideasboard.backend.model.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, MongoDbConfiguration.class})
public class IdeaRepositoryTest {

    private static final Idea IDEA = Idea.builder()
            .author("test")
            .createdDate(Date.from(LocalDate.of(2015, 4, 15).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
            .description("test description")
            .name("test name")
            .status(Status.DRAFT)
            .tags(Arrays.asList("test", "awesome"))
            .build();

    @Autowired
    private IdeaRepository ideaRepository;

    @Before
    @After
    public void cleanup() {
        ideaRepository.deleteAll();
    }

    @Test
    public void testCRUD() {
        // Create
        ideaRepository.save(IDEA);
        assertNotNull(IDEA.getId());

        // Read
        Idea retrieved = ideaRepository.findOne(IDEA.getId());
        assertEquals(IDEA, retrieved);

        // Update
        IDEA.setAuthor("another test");
        ideaRepository.save(IDEA);
        retrieved = ideaRepository.findOne(IDEA.getId());
        assertEquals("another test", retrieved.getAuthor());

        // Delete
        ideaRepository.delete(IDEA);
        assertNull(ideaRepository.findOne(IDEA.getId()));
    }

}