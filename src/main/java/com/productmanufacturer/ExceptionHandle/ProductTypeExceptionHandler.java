package com.productmanufacturer.ExceptionHandle;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.productmanufacturer.Response.ProductDetailsResponce;

@RestControllerAdvice
public class ProductTypeExceptionHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(value= {ProductTypeException.class})
	public ResponseEntity<Object> handleProductTypeException(ProductTypeException ex){
		//@Create payload contain exception details.
		//@Return resposce entity.
		ProductDetailsResponce message=new ProductDetailsResponce(ZonedDateTime.now(),false,ex.getMessage(),null);
		  if(null==ex.httpStatus) {
			  return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	   return new ResponseEntity<>(message,ex.httpStatus);
	}
	
	@ExceptionHandler(value= {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex){
		ProductDetailsResponce message=new ProductDetailsResponce(ZonedDateTime.now(),false,ex.getMessage(),null);
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
}
