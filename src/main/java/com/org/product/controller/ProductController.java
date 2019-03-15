package com.org.product.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> productList = productService.getProducts();
		productList.forEach(System.out::println);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("/getSpecificProduct/{productId}")
	public ResponseEntity<Product> getSpecificProduct(@PathVariable int productId) throws ProductNotFoundException {
		Product product = productService.getSpecificProduct(productId);
		
		String url = "http://localhost:8081/api/"+productId+"/reviews/getReviews";
		
		ResponseEntity<Review[]> reviews = restTemplate.getForEntity(url,Review[].class);	
		product.setReviews((Arrays.asList(reviews.getBody())));		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return new ResponseEntity<String>("New Product Added Successfully", HttpStatus.OK);
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable int id)
			throws ProductNotFoundException {
		productService.updateProduct(product, id);
		return new ResponseEntity<String>("Product Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) throws ProductNotFoundException {
		productService.deleteProduct(id);
		return new ResponseEntity<String>("Product Deleted Successfully", HttpStatus.OK);
	}

	/* Review Microservice Additions */

	@PostMapping("/{productId}/reviews/addReview")
	public ResponseEntity<String> addReview(@RequestBody Review productReview, @PathVariable int productId) {

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForEntity("http://localhost:8081/api/" + productId + "/reviews/addReview",
				productReview, String.class);
	}

	@PutMapping("/{productId}/reviews/updateReview/{reviewId}")
	public ResponseEntity<String> updateReview(@RequestBody Review productReview, @PathVariable int productId,
			@PathVariable int reviewId) {
		HttpEntity<Review> request = new HttpEntity<>(productReview);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange("http://localhost:8081/api/" + productId + "/reviews/updateReview/" + reviewId,
				HttpMethod.PUT, request, String.class);
	}

	@DeleteMapping("/{productId}/reviews/deleteReview/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable int productId, @PathVariable int reviewId) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange("http://localhost:8081/api/" + productId + "/reviews/deleteReview/", HttpMethod.DELETE, null, String.class);
//		restTemplate.delete("http://localhost:8081/api/" + productId + "/reviews/deleteReview/" + reviewId,
//				String.class);
	}

}
