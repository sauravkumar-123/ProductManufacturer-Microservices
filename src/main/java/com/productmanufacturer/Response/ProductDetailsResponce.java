package com.productmanufacturer.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsResponce {

	private boolean status;
	private String message;
	private Object datasource;
}
