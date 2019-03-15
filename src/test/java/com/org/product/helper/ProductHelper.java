package com.org.product.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.org.product.model.Product;
import com.org.product.model.Review;

public class ProductHelper {
	
	
	

		public List<Product> dummyAllProducts() {
			List<Review> reviews=dummyReviews();
		return Stream
				.of(new Product(1, "Asus ZenFone", "Mobile Phone in range of 15k", 15000.00, "Mobile", reviews),
						new Product(2, "Whirlpool 190L", "Whilrpool 190L Refrigerator", 25000.00, "Refrigerator",reviews),
						new Product(3, "Samsung TV", "Samsung 42 inch TV", 20000.00, "Television",reviews))
				.collect(Collectors.toList());
	}

	public Optional<Product> dummySpecificProduct() {
		List<Review> reviews=dummyReviews();
		return Optional.of(new Product(1, "Asus ZenFone", "Mobile Phone in range of 15k", 15000.00, "Mobile",reviews));

	}

	public String dummyProductList() {
		return "[{\"id\":1,\"name\":\"Asus ZenFone\",\"description\":\"Mobile Phone in range of 15k\",\"price\":15000.0,\"type\":\"Mobile\"},{\"id\":2,\"name\":\"Whirlpool 190L\",\"description\":\"Whilrpool 190L Refrigerator\",\"price\":25000.0,\"type\":\"Refrigerator\"},{\"id\":3,\"name\":\"Samsung TV\",\"description\":\"Samsung 42 inch TV\",\"price\":20000.0,\"type\":\"Television\"}]";

	}

	public ResponseEntity<Product> dummySpecificProductResponse() {
		return new ResponseEntity<Product>(dummySpecificProduct().get(), HttpStatus.OK);

	}
	
	public List<Review> dummyReviews()
	{
		List<Review> reviewList= new ArrayList<Review>();
//		reviewList.add(new ProductReview(2,"Good"));
//		reviewList.add(new ProductReview(3,"Average"));
		return reviewList;
	}

}
