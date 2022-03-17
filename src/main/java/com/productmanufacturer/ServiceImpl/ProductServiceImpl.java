package com.productmanufacturer.ServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.elasticsearch.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productmanufacturer.DAO.ProductManufacturerRepository;
import com.productmanufacturer.ExceptionHandle.GlobalException;
import com.productmanufacturer.Model.ProductManufacturer;
import com.productmanufacturer.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductManufacturerRepository ProductManufacturerRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ProductManufacturer addProductDetails(ProductManufacturer productDetailsRequest) {
		boolean isAvaliable = ProductManufacturerRepository.existsByProductCodeOrSerialNoAndIsActive(
				productDetailsRequest.getProductCode(), productDetailsRequest.getSerialNo(), 'Y');
		if (isAvaliable) {
			throw new GlobalException("Product Details Are Already Avaliable");
		} else {
			ProductManufacturer product = new ProductManufacturer();
			product.setProductCode(productDetailsRequest.getProductCode());
			product.setProductName(productDetailsRequest.getProductName());
			product.setProductbrandName(productDetailsRequest.getProductbrandName());
			product.setModelNo(productDetailsRequest.getModelNo());
			product.setSerialNo(productDetailsRequest.getSerialNo());
			product.setTotalStock(productDetailsRequest.getTotalStock());
			product.setAvaliableStock(productDetailsRequest.getAvaliableStock());
			product.setIsActive('Y');
			product.setMfgDate(productDetailsRequest.getMfgDate());
			product.setExpDate(productDetailsRequest.getExpDate());
			product.setProductprice(productDetailsRequest.getProductprice());
			product.setDescription(productDetailsRequest.getDescription());
			logger.info("Product Detail:{}" + product);
			return ProductManufacturerRepository.save(product);
		}
	}

	@Override
	public List<ProductManufacturer> getAllProduct() {
		List<ProductManufacturer> productlist = ProductManufacturerRepository.findAll('Y');
		logger.info("<------Product Details List------>" + productlist);
		if (null != productlist && !productlist.isEmpty()) {
			Collections.sort(productlist, (p1, p2) -> p1.getProductprice().compareTo(p2.getProductprice()));
			return productlist;
		} else {
			throw new NullPointerException("Product Details Not Found");
		}
	}

	@Override
	public List<ProductManufacturer> getProductBySearchkey(String searchKey) {
		List<ProductManufacturer> productList = ProductManufacturerRepository.findBySearchkey(searchKey, 'Y');
		if (null != productList && !productList.isEmpty()) {
			Collections.sort(productList, (p1, p2) -> p1.getProductprice().compareTo(p2.getProductprice()));
			return productList;
		} else {
			throw new NullPointerException("Products Details Not Found With Key: " + searchKey);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public ProductManufacturer updateProductDetailsByProductcode(String productCode,
			ProductManufacturer productDetailsRequest) {
		Optional<ProductManufacturer> chkPoint = ProductManufacturerRepository.findByProductCodeAndIsActive(productCode,
				'Y');
		if (chkPoint.isPresent()) {
			ProductManufacturer product = chkPoint.get();
			logger.info("Fetch Product Detail:{}" + product);
			product.setProductName(productDetailsRequest.getProductName());
			product.setProductbrandName(productDetailsRequest.getProductbrandName());
			product.setModelNo(productDetailsRequest.getModelNo());
			product.setTotalStock(productDetailsRequest.getTotalStock());
			product.setAvaliableStock(productDetailsRequest.getAvaliableStock());
			product.setMfgDate(productDetailsRequest.getMfgDate());
			product.setExpDate(productDetailsRequest.getExpDate());
			product.setProductprice(productDetailsRequest.getProductprice());
			product.setDescription(productDetailsRequest.getDescription());
			logger.info("Updated Product Detail:{}" + product);
			return ProductManufacturerRepository.save(product);
		} else {
			throw new NullPointerException("Product Details Not Avaliable For productCode: " + productCode);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public boolean deleteProductByProductcode(String productcode) {
		boolean status = false;
		Optional<ProductManufacturer> chkPoint = ProductManufacturerRepository.findByProductCodeAndIsActive(productcode,
				'Y');
		if (chkPoint.isPresent()) {
			ProductManufacturer product = chkPoint.get();
			product.setIsActive('N');
			status = true;
		} else {
			throw new NullPointerException("Product Details Not Avaliable For productCode: " + productcode);
		}
		return status;
	}

}
