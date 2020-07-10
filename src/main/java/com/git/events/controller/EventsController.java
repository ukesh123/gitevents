package com.git.events.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

	@GetMapping("/events")
	public ResponseEntity<String> events(String user, String repo) {
		
		return null;
	}
}
