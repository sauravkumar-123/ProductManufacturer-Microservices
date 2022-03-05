package com.productmanufacturer.ExceptionHandle;

import org.springframework.http.HttpStatus;

public class ProductTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public HttpStatus httpStatus=null;
	
	public ProductTypeException() {
		super();
	}

	public ProductTypeException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductTypeException(String message) {
		super(message);
	}
	
	public ProductTypeException(String message,HttpStatus httpStatus) {
		super(message);
		this.httpStatus=httpStatus;
	}

	public ProductTypeException(Throwable cause) {
		super(cause);
	}
	
}
