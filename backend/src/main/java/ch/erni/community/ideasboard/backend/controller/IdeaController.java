package ch.erni.community.ideasboard.backend.controller;

import ch.erni.community.ideasboard.backend.model.Idea;
import ch.erni.community.ideasboard.backend.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ideas")
public class IdeaController {

    @Autowired
    private IdeaRepository ideaRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Idea> listIdeas() {
        return ideaRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Idea> create(@RequestBody Idea idea) {
        ideaRepository.save(idea);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
