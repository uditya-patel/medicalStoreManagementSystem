package com.example.demo.exceptionhandler;

public class StockUnavailableException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public StockUnavailableException(String msg) {
		super(msg);
	}

}
