package com.joulliets.yelp.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	private String id;
	@JsonAlias("profile_url")
	private String profileUrl;
	@JsonAlias("image_url")
	private String imageUrl;
	private String name;

}
