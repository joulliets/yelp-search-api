package com.joulliets.yelp.dto;

import java.time.LocalDateTime;

import com.joulliets.yelp.domain.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewsResponseDto {
	private User user;
	private String text;
	private Integer rating;
	private LocalDateTime createdOn;
}
