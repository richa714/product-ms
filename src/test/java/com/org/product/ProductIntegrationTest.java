package com.org.product;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductMsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();	
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testGetAllProducts() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:" + port + "/api/products/getProducts", HttpMethod.GET, entity, String.class);
		String expected = "[\r\n" + 
				"    {\r\n" + 
				"        \"id\": 100,\r\n" + 
				"        \"name\": \"Asus ZenFone\",\r\n" + 
				"        \"description\": \"Mobile Phone in range of 15k\",\r\n" + 
				"        \"price\": 15000,\r\n" + 
				"        \"type\": \"Mobile\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"id\": 101,\r\n" + 
				"        \"name\": \"Samsung\",\r\n" + 
				"        \"description\": \"Samsung 190L\",\r\n" + 
				"        \"price\": 42342,\r\n" + 
				"        \"type\": \"Refrigerator\"\r\n" + 
				"    }\r\n" + 
				"]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

}
