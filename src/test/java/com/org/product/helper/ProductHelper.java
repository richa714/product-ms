package com.org.product.helper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.org.product.model.Product;

public class ProductHelper {
	
	public List<Product> allProductsDummy()
	{
		return Stream.of(new Product(1,"Asus ZenFone","Mobile Phone in range of 15k",15000.00,"Mobile"),new Product(2,"Whirlpool 190L","Whilrpool 190L Refrigerator",25000.00,"Refrigerator"),new Product(3,"Samsung TV","Samsung 42 inch TV",20000.00,"Television"))
				.collect(Collectors.toList());
	}

}
