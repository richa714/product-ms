package com.org.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.org.product.exception.ProductNotFoundException;
import com.org.product.model.Product;
import com.org.product.service.IProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	IProductService productService;

	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> productList = productService.getProducts();
		productList.forEach(System.out::println);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);

	}

	@GetMapping("/getSpecificProduct/{id}")
	public ResponseEntity<Product> getSpecificProduct(@PathVariable int id) throws ProductNotFoundException {
		Product product = productService.getSpecificProduct(id);
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

}
