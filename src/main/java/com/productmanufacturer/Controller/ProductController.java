package com.productmanufacturer.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productmanufacturer.Model.ProductManufacturer;
import com.productmanufacturer.Response.ProductDetailsResponce;
import com.productmanufacturer.Service.ProductService;

import io.swagger.annotations.Api;

@Api(value = "ProductManufacturerController", description = "This is ProductManufacturer Controller for Warehouse Product Operation")
@RestController
@RequestMapping(value = "/v1/productmanufacture")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PostMapping("/add-productdetails")
	public ResponseEntity<ProductDetailsResponce> addProductDetails(
			@Valid @ModelAttribute ProductManufacturer productDetailsRequest) {
		logger.info("<---Request Payload--->" + productDetailsRequest);
		ProductManufacturer product = productService.addProductDetails(productDetailsRequest);
		if (null != product) {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(true, "Product Detail Saved", product), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(false, "Unable To Save Product Detail", product),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-all-productdetails")
	public ResponseEntity<ProductDetailsResponce> getProductDetails() {
		List<ProductManufacturer> prodList = productService.getAllProduct();
		logger.info("<-----Product List----->" + prodList);
		if (null != prodList && !prodList.isEmpty()) {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(true, "Product Details List Fetched", prodList), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(false, "Unable To Fetch Product Details List", prodList),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get-productdetailsByKey")
	public ResponseEntity<ProductDetailsResponce> getProductBySearchkey(@RequestParam("searchKey") String searchKey) {
		logger.info("<---Search Key--->" + searchKey);
		List<ProductManufacturer> prodList = productService.getProductBySearchkey(searchKey);
		logger.info("<-----Product List----->" + prodList);
		if (null != prodList && !prodList.isEmpty()) {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(true, "Product Details Fetched", prodList), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(false, "Unable To Fetch Product Details", prodList),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get-productBycode/{productCode}")
	public ResponseEntity<ProductDetailsResponce> getProductByProductCode(
			@PathVariable("productCode") String productCode) {
		ProductManufacturer product = productService.getProductByProductCode(productCode);
		if (null != product) {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(true, "Product Detail Fetched", product), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(false, "Product Detail Not Found", product), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update-productdetails/{productCode}")
	public ResponseEntity<ProductDetailsResponce> updateProductdetailsByProductcode(
			@PathVariable("productCode") String productCode,
			@Valid @ModelAttribute ProductManufacturer productDetailsRequest) {
		logger.info("<---ProductCode And Request Payload--->" + productCode + " " + productDetailsRequest);
		ProductManufacturer product = productService.updateProductDetailsByProductcode(productCode,
				productDetailsRequest);
		logger.info("<-----Updated Product Detail----->" + product);
		if (null != product) {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(true, "Product Details Updated Successfully", product), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(false, "Unable To Update Product Details", product),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete-productdetails")
	public ResponseEntity<ProductDetailsResponce> deleteProductdetailsByProductcode(
			@RequestParam("productCode") String productCode) {
		logger.info("<---ProductCode --->" + productCode);
		boolean status = productService.deleteProductByProductcode(productCode);
		if (status) {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(true, "Product Details Deleted Successfully", null), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductDetailsResponce>(
					new ProductDetailsResponce(false, "Unable To Delete Product Details", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
