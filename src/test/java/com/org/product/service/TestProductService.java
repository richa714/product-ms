package com.org.product.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.org.product.exception.ProductNotFoundException;
import com.org.product.helper.ProductHelper;
import com.org.product.model.Product;
import com.org.product.repository.IProductRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestProductService {

	@Mock
	IProductRepository mockProductRepository;

	@InjectMocks
	IProductService mockProductService = new ProductServiceImpl();

	@Test
	public void testGetProducts() {
		ProductHelper helper = new ProductHelper();
		List<Product> expectedProductList = helper.dummyAllProducts();
		when(mockProductRepository.findAll()).thenReturn(expectedProductList);
		List<Product> actualProductList = mockProductService.getProducts();
		assertEquals("Actual matches expected", expectedProductList.size(), actualProductList.size());

	}

	@Test
	public void testGetSpecificProduct() throws ProductNotFoundException {
		ProductHelper helper = new ProductHelper();
		Optional<Product> expectedProduct = helper.dummySpecificProduct();
		when(mockProductRepository.findById(1)).thenReturn(expectedProduct);
		Product actualProduct = mockProductService.getSpecificProduct(1);
		assertEquals("Actual matches expected", expectedProduct.get().getName(), actualProduct.getName());

	}

	@Test(expected = ProductNotFoundException.class)
	public void testExceptionForGetSpecificProduct() throws ProductNotFoundException {
		mockProductService.getSpecificProduct(5);
	}

	@Test
	public void testUpdateProduct() throws ProductNotFoundException {
		ProductHelper helper = new ProductHelper();
		Optional<Product> productToUpdate = helper.dummySpecificProduct();
		when(mockProductRepository.findById(0)).thenReturn(productToUpdate);
		mockProductService.updateProduct(productToUpdate.get(), 0);
		verify(mockProductRepository, times(1)).save(productToUpdate.get());

	}

	@Test
	public void testAddProduct() throws ProductNotFoundException {
		ProductHelper helper = new ProductHelper();
		Optional<Product> productToAdd = helper.dummySpecificProduct();
		mockProductService.addProduct(productToAdd.get());
		verify(mockProductRepository, times(1)).save(productToAdd.get());

	}

}
