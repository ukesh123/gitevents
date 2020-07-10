package com.git.events.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	
	private String id;
	
	private String type;
	
	private Actor actor;
	
	@JsonProperty("created_at")
	private String createdAt;
}