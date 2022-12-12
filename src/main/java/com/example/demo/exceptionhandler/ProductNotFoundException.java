package com.example.demo.exceptionhandler;

public class ProductNotFoundException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException(String msg) {
		super(msg);
	}

}
