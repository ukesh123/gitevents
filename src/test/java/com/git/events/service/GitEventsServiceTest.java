package com.git.events.service;

import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.git.events.vo.Actor;
import com.git.events.vo.Event;

public class GitEventsServiceTest {

	
	@InjectMocks
	private GitEventsService gitEventsService;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllEvents() {
		@SuppressWarnings("unchecked")
		ResponseEntity<Object> responseEntity = Mockito.mock(ResponseEntity.class);
		Event[] eventArr = new Event[2];
		eventArr[0] = getEventObj("pushEvent");
		eventArr[1] = getEventObj("createEvent");
		when(responseEntity.getBody()).thenReturn(eventArr);
		when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(responseEntity);
		List<Event> events = gitEventsService.getEvents("ukesh123", "gitevents", null);
		Assert.assertTrue(events.size() ==  eventArr.length);
	}
	
	@Test
	public void testGetSelectedEvents() {
		@SuppressWarnings("unchecked")
		ResponseEntity<Object> responseEntity = Mockito.mock(ResponseEntity.class);
		Event[] eventArr = new Event[2];
		eventArr[0] = getEventObj("pushEvent");
		eventArr[1] = getEventObj("createEvent");
		when(responseEntity.getBody()).thenReturn(eventArr);
		when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(responseEntity);
		List<Event> events = gitEventsService.getEvents("ukesh123", "gitevents", "createEvent");
		Assert.assertTrue(events.size() ==  1);
		Event createEvent = events.get(0);
		Assert.assertEquals("createEvent", createEvent.getType());
	}

	private Event getEventObj(String eventType) {
		Event event = new Event();
		event.setCreatedAt(new Date().toString());
		event.setId("12871892782");
		event.setType(eventType);
		Actor actor = new Actor();
		actor.setAvatarUrl("https://avatars.githubusercontent.com/u/68005223?");
		actor.setDisplayLogin("ukesh123");
		actor.setGravatarId("");
		actor.setId(1);
		actor.setLogin("ukesh123");
		actor.setUrl("https://api.github.com/users/ukesh123");
		event.setActor(actor);
		return event;
	}
	
}
