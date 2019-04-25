package com.org.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	DiscoveryClient discoveryClient;

	@Autowired
	ReviewClient reviewClient;

	Link productSelfLink = ControllerLinkBuilder.linkTo(ProductController.class).withSelfRel();

	@GetMapping
	public ResponseEntity<Object> getProducts() {
		List<Product> productList = productService.getProducts();
		productList.forEach(System.out::println);
		// Link
		// selfLink=ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ProductController.class).getProducts()).withSelfRel();
		Resources<Product> resource = new Resources<>(productList, productSelfLink);
		return ResponseEntity.ok(resource);
		// return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Object> getSpecificProduct(@PathVariable int productId) throws ProductNotFoundException {
		Product product = productService.getSpecificProduct(productId);
		// String url = getReviewServiceInstance() + "/api/" + productId + "/reviews/";
		// String url = "http://localhost:8081/api/" + productId + "/reviews/";
		// ResponseEntity<Review[]> reviews =
		// restTemplate.getForEntity(url,Review[].class);
		// ResponseEntity<Review[]> reviews=restTemplate.exchange(url, HttpMethod.GET,
		// null, Review[].class);
		ResponseEntity<List<Review>> reviews = reviewClient.getReview(productId);
		//System.out.println(reviews.getBody().);
		product.setReviews(reviews.getBody());

//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<List<Review>> reviews = restTemplate.exchange(url, HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<Review>>() {
//				});
//
//		product.setReviews(reviews.getBody());

		Link selfLink = ControllerLinkBuilder.linkTo(ProductController.class).slash(product.getId()).withSelfRel();
		Link reviewLink = ControllerLinkBuilder.linkTo(ProductController.class).slash(product.getId()).slash("reviews")
				.withRel("reviews");
		Resource<Product> resource = new Resource<Product>(product, selfLink, reviewLink);
		return ResponseEntity.ok(resource);
	}

	@PostMapping
	public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
		productService.addProduct(product);
		Resource<String> resource = new Resource<String>("New Product Added Successfully", productSelfLink);
		return ResponseEntity.ok(resource);
		// return new ResponseEntity<String>("New Product Added Successfully",
		// HttpStatus.CREATED);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Object> updateProduct(@RequestBody @Valid Product product, @PathVariable int productId)
			throws ProductNotFoundException {
		productService.updateProduct(product, productId);
		Link selfLink = ControllerLinkBuilder.linkTo(ProductController.class).slash(product.getId()).withSelfRel();
		Resource<String> resource = new Resource<String>("Product Updated Successfully", selfLink);
		return ResponseEntity.ok(resource);
		//return new ResponseEntity<String>("Product Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable int productId) throws ProductNotFoundException {
		productService.deleteProduct(productId);
		Link selfLink = ControllerLinkBuilder.linkTo(ProductController.class).slash("a").withSelfRel();
		Resource<String> resource = new Resource<String>("Product Deleted Successfully", selfLink);
		return ResponseEntity.ok(resource);
		//return new ResponseEntity<String>("Product Deleted Successfully", HttpStatus.OK);
	}

	/* Review Microservice Additions */

	@PostMapping("/{productId}/reviews")
	public ResponseEntity<String> addReview(@RequestBody @Valid Review productReview, @PathVariable int productId) {
		return reviewClient.addReview(productReview, productId);
//		RestTemplate restTemplate = new RestTemplate();
//		String url=getReviewServiceInstance()+"/api/"+ productId + "/reviews/";
//		return restTemplate.postForEntity(url,
//				productReview, String.class);
	}

	@PutMapping("/{productId}/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@RequestBody @Valid Review productReview, @PathVariable int productId,
			@PathVariable int reviewId) {
		return reviewClient.updateReview(productReview, productId, reviewId);
//		HttpEntity<Review> request = new HttpEntity<>(productReview);
//		RestTemplate restTemplate = new RestTemplate();
//		String url=getReviewServiceInstance()+"/api/"+ productId + "/reviews/"+reviewId;
//		return restTemplate.exchange(url,
//				HttpMethod.PUT, request, String.class);
	}

	@DeleteMapping("/{productId}/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable int productId, @PathVariable int reviewId) {
		return reviewClient.deleteReview(productId, reviewId);
//		RestTemplate restTemplate = new RestTemplate();
//		String url=getReviewServiceInstance()+"/api/"+ productId + "/reviews/"+reviewId;
//		return restTemplate.exchange(url,
//				HttpMethod.DELETE, null, String.class);
//		restTemplate.delete("http://localhost:8081/api/" + productId + "/reviews/deleteReview/" + reviewId,
//				String.class);
	}

//	public String getReviewServiceInstance() {
//		System.out.println(this.discoveryClient.getServices());
//		System.out.println(this.discoveryClient.getInstances("ReviewApplication"));
//		return this.discoveryClient.getInstances("ReviewApplication").get(0).getUri().toString();
//	}

}
