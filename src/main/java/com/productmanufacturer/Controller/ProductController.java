package com.productmanufacturer.Controller;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanufacturer.Request.ProductDetailsRequest;
import com.productmanufacturer.Response.ProductDetailsResponce;
import com.productmanufacturer.Service.ProductService;

import io.swagger.annotations.Api;

@Api(value = "ProductManufacturerController" ,description = "This is ProductManufacturer Controller for Warehouse Product Operation")
@RestController
@RequestMapping(value = "/v1/productapi")
public class ProductController {

	private static final Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add-productdetails")
	public ResponseEntity<ProductDetailsResponce> addProductDetails(@RequestBody ProductDetailsRequest productDetailsRequest) throws ParseException{
		logger.info("<---Request Payload--->"+productDetailsRequest);
		if(null!=productDetailsRequest.getProductid() && !productDetailsRequest.getProductid().isEmpty() &&
		   null!=productDetailsRequest.getProductname() && !productDetailsRequest.getProductname().isEmpty()&&
		   null!=productDetailsRequest.getProductcode() && !productDetailsRequest.getProductcode().isEmpty() &&
		   null!=productDetailsRequest.getProductbrand() && !productDetailsRequest.getProductbrand().isEmpty() &&
		   null!=productDetailsRequest.getProductmfgdate() && !productDetailsRequest.getProductmfgdate().isEmpty()) {
		 String result=productService.addProductDetails(productDetailsRequest);
		     if (result.equalsIgnoreCase("success")) {
			    return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), true, "Product Details Saved", result),HttpStatus.CREATED);	
		     }
		}else {
			return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "Input Field Missing!!Please Check", null),HttpStatus.BAD_REQUEST);
		}
	return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "Unable To Save Product Details", null),HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping("/get-all-productdetails")
	public ResponseEntity<ProductDetailsResponce> getProductDetails(){
		List<Map<String, Object>> prodList=productService.getAllProduct();
		logger.info("<-----Product List----->"+prodList);
		if(null!=prodList && !prodList.isEmpty()) {
			 return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), true, "Product Details List Fetched", prodList),HttpStatus.OK);
		}else {
			return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "Data Not Found", prodList),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/get-productdetail-by-productcode/{productcode}")
	public ResponseEntity<ProductDetailsResponce> getProductDetailByProductcode(@PathVariable(value = "productcode") String productcode){
	 if (null!=productcode) {
		List<Map<String, Object>> prodList=productService.getProductByProductcode(productcode);
		logger.info("<-----Product Detail----->"+prodList);
		if(null!=prodList && !prodList.isEmpty()) {
			 return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), true, "Product Detail Fetched", prodList),HttpStatus.OK);
		}else {
			return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "Data Not Found", prodList),HttpStatus.NOT_FOUND);
		}
	 }else {
		 return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "ProductCode Missing!!Please Check", null),HttpStatus.BAD_REQUEST);	 
	 }
	}
	
	@PutMapping("/update-productdetail-by-productcode/{productcode}")
	public ResponseEntity<ProductDetailsResponce> updateProductDetail(@PathVariable(value = "productcode") String productcode,@RequestBody ProductDetailsRequest productDetailsRequest){
	 if (null!=productcode) {	
		logger.info("<---Request Payload--->"+productDetailsRequest);
		if(null!=productDetailsRequest.getProductname() && !productDetailsRequest.getProductname().isEmpty()&&
		   null!=productDetailsRequest.getProductcode() && !productDetailsRequest.getProductcode().isEmpty() &&
		   null!=productDetailsRequest.getProductbrand() && !productDetailsRequest.getProductbrand().isEmpty()) {
		 String result=productService.updateProductDetailsByProductcode(productcode, productDetailsRequest);
		     if (result.equalsIgnoreCase("success")) {
			    return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), true, "Product Details Updated", result),HttpStatus.OK);	
		     }
		}else {
			return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "Input Parameter Missing!!Please Check", null),HttpStatus.BAD_REQUEST);
		}
	 }else {
		 return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "ProductCode Missing!!Please Check", null),HttpStatus.BAD_REQUEST);
	 }
  return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "Error While Update Data", null),HttpStatus.INTERNAL_SERVER_ERROR);	 
 }
	
	
	@DeleteMapping("/delete-productdetail-by-productcode/{productcode}")
	public ResponseEntity<ProductDetailsResponce> deleteProductDetail(@PathVariable(value = "productcode") String productcode){
	 if (null!=productcode) {	
		 String result=productService.deleteProductByProductcode(productcode);
		     if (result.equalsIgnoreCase("success")) {
			    return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), true, "Product Details deleted Sucessful", result),HttpStatus.OK);	
		     }
	}else {
		return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "ProductCode Missing!!Please Check", null),HttpStatus.BAD_REQUEST);	
	}
 return new ResponseEntity<ProductDetailsResponce>(new ProductDetailsResponce(ZonedDateTime.now(), false, "Error While Delete Data", null),HttpStatus.INTERNAL_SERVER_ERROR);	 
 }	 
}
