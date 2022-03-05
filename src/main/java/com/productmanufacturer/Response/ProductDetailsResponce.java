package com.productmanufacturer.Response;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsResponce {

	private ZonedDateTime timestamp;
	private boolean status;
	private String message;
	private Object data;
}
