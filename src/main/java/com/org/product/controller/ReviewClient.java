package com.org.product.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.product.model.Review;

@FeignClient(name="zuul-api-gateway", fallback=ReviewClientFallback.class)
public interface ReviewClient {
	
	@PostMapping("/api/{productId}/reviews/")
	public ResponseEntity<String> addReview(@RequestBody Review productReview, @PathVariable int productId);
	@PutMapping("/api/{productId}/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@RequestBody Review productReview, @PathVariable int productId,
			@PathVariable int reviewId);
	@DeleteMapping("/api/{productId}/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable int productId, @PathVariable int reviewId);
	@GetMapping("/api/{productId}/reviews/")
	public ResponseEntity<List<Review>> getReview(@PathVariable int productId);
	
	
	
	
}