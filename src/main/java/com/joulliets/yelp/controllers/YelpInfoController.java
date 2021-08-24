package com.joulliets.yelp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joulliets.yelp.dto.ReviewsResponseDto;
import com.joulliets.yelp.services.YelpInfoService;

@RestController
public class YelpInfoController {

	@Value("${yelp-config.business-name}")
	private String businessNameByDefault;
	@Autowired
	private YelpInfoService yelpInfoService;

	/**
	 * This method will take a GET request to find reviews of the 
	 * company specified by parameters in Yelp <i>https://www.yelp.com/</i> records.
	 * @param businessName This will be the business identifier registered in Yelp 
	 * database. If no name is passed as parameter, the app will use the default name specified in the app config file.
	 * @return List of reviews recorded in Yelp database.
	 */
	@GetMapping("/reviews") 
	public List<ReviewsResponseDto> getBusinessReviews(
			@RequestParam(required = false, name = "name") String businessName) {
		
		if (!StringUtils.hasText(businessName)) {
			businessName = businessNameByDefault;
		}
		
		return yelpInfoService.getReviewsByBusinessName(businessName);
	}
}
