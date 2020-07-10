package com.git.events.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.git.events.vo.Event;

@Service
public class GitEventsService {

	private static final String GIT_EVENTS_URL = "https://api.github.com/repos/:owner/:repo/events";
	private static final String GIT_OWNER_PLACEHOLDER = ":owner";
	private static final String GIT_REPO_PLACEHOLDER = ":repo";

	@Autowired
	private RestTemplate restTemplate;

	public List<Event> getEvents(String owner, String repo, String eventType) {
		return filterEventsByEventType(eventType, getEventsFromGit(owner, repo));
	}

	private Event[] getEventsFromGit(String owner, String repo) {
		return restTemplate.getForEntity(getGitUrl(owner, repo), Event[].class).getBody();
	}

	private String getGitUrl(String owner, String repo) {
		String gitEventsUrl = GIT_EVENTS_URL;
		gitEventsUrl = gitEventsUrl.replace(GIT_OWNER_PLACEHOLDER, owner);
		gitEventsUrl = gitEventsUrl.replace(GIT_REPO_PLACEHOLDER, repo);
		return gitEventsUrl;
	}

	private List<Event> filterEventsByEventType(String eventType, Event[] events) {
		if (null != eventType)
			return Arrays.asList(events).stream().filter(event -> {
				return event.getType().toLowerCase().contains(eventType.toLowerCase());
			}).collect(Collectors.toList());
		else
			return Arrays.asList(events);
	}
}
