package com.org.product.service;

import java.util.List;



import com.org.product.exception.ProductNotFoundException;
import com.org.product.model.Product;

public interface IProductService {
	public List<Product> getProducts();
	public Product getSpecificProduct(int id) throws ProductNotFoundException;
	public void addProduct(Product product);
	public void updateProduct(Product product,int id) throws ProductNotFoundException;
	public void deleteProduct(int id) throws ProductNotFoundException;
	

}
