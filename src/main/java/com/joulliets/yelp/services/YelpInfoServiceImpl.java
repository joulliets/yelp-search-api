package com.joulliets.yelp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.joulliets.yelp.domain.YelpReviews;
import com.joulliets.yelp.dto.ReviewsResponseDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class YelpInfoServiceImpl implements YelpInfoService {
	
	//Reading values from config file.
	@Value("${yelp-config.base-url}")
	private String baseUrl;
	@Value("${yelp-config.api-key}")
	private String apiKey;
	@Value("${yelp-config.date-time-format}")
	private String dateTimeFormat;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<ReviewsResponseDto> getReviewsByBusinessName(String businessName) {
		List<ReviewsResponseDto> resultList = new ArrayList<>();
		String pathUrl = baseUrl + businessName + "/reviews";
		HttpHeaders header = new HttpHeaders();
		//Adding authentication key in the header of the request as per API requirements.
		header.add("Authorization", "Bearer " + apiKey);
		HttpEntity<String> httpEntity = new HttpEntity<>(header);
		
		try {
			ResponseEntity<YelpReviews> reviews = restTemplate
					.exchange(pathUrl, HttpMethod.GET, httpEntity, YelpReviews.class);
			log.info("Response elements[{}].", reviews.getBody().getReviews().size());
			
			//Transforming API response in DTOs
			reviews.getBody().getReviews().forEach(rev -> {
				ReviewsResponseDto revItem = new ReviewsResponseDto();
				revItem.setUser(rev.getUser());
				revItem.setText(rev.getText());
				revItem.setRating(rev.getRating());
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
				LocalDateTime createdDateTime = LocalDateTime.parse(rev.getTimeCreated(), formatter);
				revItem.setCreatedOn(createdDateTime);
				
				resultList.add(revItem);
			});
			
		} catch (Exception e) {
			//At this point another mechanism can be implemented to handle exceptions in a different way.
			log.error("An error was found trying to retrieve reviews for: {}. Error: {}", businessName, e.getMessage());
		}
		
		return resultList;
	}

}
