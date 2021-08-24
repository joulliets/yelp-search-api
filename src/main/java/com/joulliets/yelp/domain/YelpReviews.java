package com.joulliets.yelp.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YelpReviews {
	private List<Review> reviews;
	private Integer total;
	private List<String> possibleLanguages;
}
