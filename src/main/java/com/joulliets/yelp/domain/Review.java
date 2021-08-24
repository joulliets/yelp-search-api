package com.joulliets.yelp.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Review {
	private String id;
	private String url;
	private String text;
	private Integer rating;
	@JsonAlias("time_created")
	private String timeCreated;
	private User user;
}
