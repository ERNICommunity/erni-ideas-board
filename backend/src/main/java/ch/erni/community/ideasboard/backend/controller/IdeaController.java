package ch.erni.community.ideasboard.backend.controller;

import ch.erni.community.ideasboard.backend.model.Idea;
import ch.erni.community.ideasboard.backend.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rap on 3/16/2015.
 */
@RestController
public class IdeaController {

    @Autowired
    private IdeaRepository ideaRepository;

    @RequestMapping(value = "/ideas",method = RequestMethod.POST)
    public void create(@RequestParam Idea idea) {
        ideaRepository.save(idea);
    }



}
