package com.git.events.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Actor {
	
	private float id;
	
	private String login;
	
	@JsonProperty("display_login")
	private String displayLogin;
	
	@JsonProperty("gravatar_id")
	private String gravatarId;
	
	private String url;
	
	@JsonProperty("avatar_url")
	private String avatarUrl;
}