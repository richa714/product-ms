package com.org.product.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.junit.MockitoJUnitRunner;

import com.org.product.helper.ProductHelper;
import com.org.product.model.Product;
import com.org.product.repository.IProductRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestProductService {

	@Mock
	IProductRepository mockProductRepository;

	@InjectMocks
	IProductService mockProductService=new ProductServiceImpl();
	
	
	@Test
	public void testGetProducts() {
		ProductHelper helper = new ProductHelper();
		List<Product> expectedProductList = helper.allProductsDummy();
		when(mockProductRepository.findAll()).thenReturn(expectedProductList);
		List<Product> actualProductList = mockProductService.getProducts();
		assertEquals("Actual matches expected", expectedProductList.size(), actualProductList.size());

	}
}
