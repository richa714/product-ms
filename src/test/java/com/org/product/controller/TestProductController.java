package com.org.product.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.org.product.helper.ProductHelper;
import com.org.product.model.Product;
import com.org.product.service.IProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class TestProductController {

	@MockBean
	IProductService mockProductService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testGetProducts() throws Exception {
		ProductHelper helper = new ProductHelper();
		String expectedProductListResponse = helper.dummyProductList();
		List<Product> expectedProductList = helper.dummyAllProducts();
		when(mockProductService.getProducts()).thenReturn(expectedProductList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/getProducts")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("Expected matches actual", expectedProductListResponse,
				result.getResponse().getContentAsString());

	}

	@Test
	public void testGetSpecificProduct() throws Exception {
		ProductHelper helper = new ProductHelper();
		ResponseEntity<Product> expectedProductResponse = helper.dummySpecificProductResponse();		
		Product expectedProduct = helper.dummySpecificProduct().get();
		when(mockProductService.getSpecificProduct(1)).thenReturn(expectedProduct);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/getSpecificProduct/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("Expected matches actual", expectedProductResponse.getBody().toString(),
				result.getResponse().getContentAsString());

	}

//	@Test
//	public void testAddProduct() throws Exception {
//		ProductHelper helper = new ProductHelper();
//		ResponseEntity<List<Product>> expectedProductListResponse = helper.dummyProductResponse();
//		List<Product> expectedProductList = helper.dummyAllProducts();
//		//when(mockProductService.addProduct(Mockito.any(Product.class)).thenReturn(expectedProductList);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/getProducts")
//				.accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		assertEquals("Expected matches actual", expectedProductListResponse.getBody().toString(),
//				result.getResponse().getContentAsString());
//
//	}
//
//	@Test
//	public void testUpdateProduct() throws Exception {
//		ProductHelper helper = new ProductHelper();
//		ResponseEntity<List<Product>> expectedProductListResponse = helper.dummyProductResponse();
//		List<Product> expectedProductList = helper.dummyAllProducts();
//		when(mockProductService.getProducts()).thenReturn(expectedProductList);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/getProducts")
//				.accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		
//		assertEquals("Expected matches actual", expectedProductListResponse.getBody().toString(),
//				result.getResponse().getContentAsString());
//
//	}
//
//	@Test
//	public void testDeleteProduct() throws Exception {
//		ProductHelper helper = new ProductHelper();
//		ResponseEntity<List<Product>> expectedProductListResponse = helper.dummyProductResponse();
//		List<Product> expectedProductList = helper.dummyAllProducts();
//		when(mockProductService.getProducts()).thenReturn(expectedProductList);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/getProducts")
//				.accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		assertEquals("Expected matches actual", expectedProductListResponse.getBody().toString(),
//				result.getResponse().getContentAsString());
//
//	}

}
