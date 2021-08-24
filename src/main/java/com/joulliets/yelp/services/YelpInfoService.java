package com.joulliets.yelp.services;

import java.util.List;

import com.joulliets.yelp.dto.ReviewsResponseDto;

public interface YelpInfoService {
	
	List<ReviewsResponseDto> getReviewsByBusinessName(String businessName);
}
