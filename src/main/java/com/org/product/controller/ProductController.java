package com.org.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.org.product.exception.ProductNotFoundException;
import com.org.product.model.Product;
import com.org.product.model.Review;
import com.org.product.service.IProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	IProductService productService;

	@GetMapping("/")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> productList = productService.getProducts();
		productList.forEach(System.out::println);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getSpecificProduct(@PathVariable int productId) throws ProductNotFoundException {
		Product product = productService.getSpecificProduct(productId);

		String url = "http://localhost:8081/api/" + productId + "/reviews/";
		// ResponseEntity<Review[]> reviews =
		// restTemplate.getForEntity(url,Review[].class);
		// ResponseEntity<Review[]> reviews=restTemplate.exchange(url, HttpMethod.GET,
		// null, Review[].class);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Review>> reviews = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Review>>() {
				});

		product.setReviews(reviews.getBody());
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return new ResponseEntity<String>("New Product Added Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable int productId)
			throws ProductNotFoundException {
		productService.updateProduct(product, productId);
		return new ResponseEntity<String>("Product Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId) throws ProductNotFoundException {
		productService.deleteProduct(productId);
		return new ResponseEntity<String>("Product Deleted Successfully", HttpStatus.OK);
	}

	/* Review Microservice Additions */
	
	@PostMapping("/{productId}/reviews")
	public ResponseEntity<String> addReview(@RequestBody Review productReview, @PathVariable int productId) {

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForEntity("http://localhost:8081/api/" + productId + "/reviews/",
				productReview, String.class);
	}

	@PutMapping("/{productId}/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@RequestBody Review productReview, @PathVariable int productId,
			@PathVariable int reviewId) {
		HttpEntity<Review> request = new HttpEntity<>(productReview);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange("http://localhost:8081/api/" + productId + "/reviews/" + reviewId,
				HttpMethod.PUT, request, String.class);
	}

	@DeleteMapping("/{productId}/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable int productId, @PathVariable int reviewId) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange("http://localhost:8081/api/" + productId + "/reviews/"+ reviewId,
				HttpMethod.DELETE, null, String.class);
//		restTemplate.delete("http://localhost:8081/api/" + productId + "/reviews/deleteReview/" + reviewId,
//				String.class);
	}

}
