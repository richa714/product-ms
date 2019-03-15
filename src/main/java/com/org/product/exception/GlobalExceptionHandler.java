package com.org.product.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.org.product.model.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler {
	

	private final Logger LOG= LoggerFactory.getLogger(this.getClass());
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(HttpServletRequest request)
	{
		LOG.info("Exception occurred in url:: {}",request.getRequestURL());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.NOT_FOUND,"Product Not Found in DB"), HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request)
	{
		LOG.info("Null Pointer Exception occurred in url:: {}",request.getRequestURL());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST,"Some Exception occurred"), HttpStatus.NOT_FOUND);
		
	}
}
