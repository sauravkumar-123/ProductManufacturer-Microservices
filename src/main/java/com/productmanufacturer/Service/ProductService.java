package com.productmanufacturer.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productmanufacturer.Model.ProductManufacturer;

@Service
public interface ProductService {
	public ProductManufacturer addProductDetails(ProductManufacturer productDetailsRequest);

	public List<ProductManufacturer> getAllProduct();

	public List<ProductManufacturer> getProductBySearchkey(String serachkey);

	public ProductManufacturer getProductByProductCode(String productCode);

	public ProductManufacturer updateProductDetailsByProductcode(String productCode,
			ProductManufacturer productDetailsRequest);

	public boolean deleteProductByProductcode(String productcode);
}
