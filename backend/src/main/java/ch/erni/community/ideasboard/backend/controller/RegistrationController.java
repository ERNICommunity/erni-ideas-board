package ch.erni.community.ideasboard.backend.controller;

import ch.erni.community.ideasboard.backend.model.CommunityUser;
import ch.erni.community.ideasboard.backend.model.Idea;
import ch.erni.community.ideasboard.backend.service.CommunityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author rap
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private CommunityUserService communityUserService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Idea> create(@RequestBody @Valid CommunityUser communityUser) {
		communityUserService.register(communityUser);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
