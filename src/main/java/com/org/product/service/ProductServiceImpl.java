package com.org.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.product.exception.ProductNotFoundException;
import com.org.product.model.Product;
import com.org.product.repository.IProductRepository;

@Component
public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);

	}

	@Override
	public void updateProduct(Product product, int id) throws ProductNotFoundException {
		Optional<Product> productInDb = productRepository.findById(id);
		if (!productInDb.isPresent())
			throw new ProductNotFoundException(id);
		product.setId(id);
		productRepository.save(product);

	}

	@Override
	public void deleteProduct(int id) throws ProductNotFoundException {
		Optional<Product> productInDb = productRepository.findById(id);

		if (!productInDb.isPresent())
			throw new ProductNotFoundException(id);

		productRepository.deleteById(id);

	}

	@Override
	public Product getSpecificProduct(int id) throws ProductNotFoundException {
		Optional<Product> productInDb = productRepository.findById(id);
		if (!productInDb.isPresent())
			throw new ProductNotFoundException(id);
		System.out.println(productInDb.get());
		return productInDb.get();
	}

}
