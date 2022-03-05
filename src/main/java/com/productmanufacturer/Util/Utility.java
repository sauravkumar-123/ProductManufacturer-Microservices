package com.productmanufacturer.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.productmanufacturer.ExceptionHandle.ProductTypeException;

public class Utility {

	public static Date StringToDateConvert(String date,String msg) {
		Date converteddate=null;
		try {
             converteddate= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
        }catch(Exception e){
        	e.printStackTrace();
        	throw new ProductTypeException(msg);
        }
	  return converteddate;
	}	
}
