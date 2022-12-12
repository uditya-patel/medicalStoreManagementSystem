package com.example.demo.exceptionhandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(BillNotFoundException.class)
	public ResponseEntity<String> billNotFound(BillNotFoundException bnfe) {
		return new ResponseEntity<>(bnfe.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFound(ProductNotFoundException pnfe) {
		return new ResponseEntity<>(pnfe.getMessage(), HttpStatus.NOT_FOUND);
	}

}
