package com.org.product.helper;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.org.product.model.Product;

public class ProductHelper {
	
	public List<Product> dummyAllProducts()
	{
		return Stream.of(new Product(1,"Asus ZenFone","Mobile Phone in range of 15k",15000.00,"Mobile"),new Product(2,"Whirlpool 190L","Whilrpool 190L Refrigerator",25000.00,"Refrigerator"),new Product(3,"Samsung TV","Samsung 42 inch TV",20000.00,"Television"))
				.collect(Collectors.toList());
	}
	
	
	public Optional<Product> dummySpecificProduct()
	{
		return Optional.of(new Product(1,"Asus ZenFone","Mobile Phone in range of 15k",15000.00,"Mobile"));		
		
	}
	
	public ResponseEntity<List<Product>> dummyProductResponse()
	{
		return new ResponseEntity<List<Product>>(dummyAllProducts(), HttpStatus.OK);
		
		
	}

}
