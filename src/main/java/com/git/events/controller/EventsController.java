package com.git.events.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.git.events.service.GitEventsService;
import com.git.events.vo.Event;

@RestController
public class EventsController {

	@Autowired
	private GitEventsService gitEventsService;

	@GetMapping("/events")
	public ResponseEntity<List<Event>> events(@RequestParam(required = true) String owner, 
			@RequestParam(required = true) String repo, String eventType) {
		return new ResponseEntity<List<Event>>(gitEventsService.getEvents(owner, repo, eventType), HttpStatus.OK);
	}
}
