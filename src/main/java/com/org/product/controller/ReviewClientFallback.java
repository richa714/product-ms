package com.org.product.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.org.product.model.Review;

@Configuration
public class ReviewClientFallback implements ReviewClient {

	@Override
	public ResponseEntity<String> addReview(Review productReview, int productId) {
		return new ResponseEntity<>("Review Service Down", HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	public ResponseEntity<String> updateReview(Review productReview, int productId, int reviewId) {
		return new ResponseEntity<>("Review Service Down", HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	public ResponseEntity<String> deleteReview(int productId, int reviewId) {
		return new ResponseEntity<>("Review Service Down", HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	public ResponseEntity<List<Review>> getReview(int productId) {
		Review defaultReview = new Review();
		defaultReview.setComments("Review Service Down");
		return new ResponseEntity<>(Arrays.asList(defaultReview), HttpStatus.SERVICE_UNAVAILABLE);
	}

}
