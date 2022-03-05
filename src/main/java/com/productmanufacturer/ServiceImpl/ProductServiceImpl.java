package com.productmanufacturer.ServiceImpl;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.productmanufacturer.Dao.ProductManufacturerRepository;
import com.productmanufacturer.ExceptionHandle.ProductTypeException;
import com.productmanufacturer.Model.ProductManufacturer;
import com.productmanufacturer.Request.ProductDetailsRequest;
import com.productmanufacturer.Service.ProductService;
import com.productmanufacturer.Util.Utility;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductManufacturerRepository ProductManufacturerRepository;
	
	@Override
	public String addProductDetails(ProductDetailsRequest productDetailsRequest) throws ParseException {
		String msg="";
		ProductManufacturer productmfg=ProductManufacturerRepository.findByProductidOrProductcode(productDetailsRequest.getProductid(), productDetailsRequest.getProductcode());
        if(null!=productmfg) {
        	throw new ProductTypeException("Product Details Already Present!!!!- Try To Save Another Product", HttpStatus.ALREADY_REPORTED);
        }else {
        	ProductManufacturer productmnuf=new ProductManufacturer();
        	productmnuf.setProductid(productDetailsRequest.getProductid());
        	productmnuf.setProductname(productDetailsRequest.getProductname());
        	productmnuf.setProductcode(productDetailsRequest.getProductcode());
        	productmnuf.setProductbrand(productDetailsRequest.getProductbrand());
        	productmnuf.setProductmfgdate(Utility.StringToDateConvert(productDetailsRequest.getProductmfgdate(), "Unable To Save ManufacturerDate"));
        	productmnuf.setProductprice(productDetailsRequest.getProductprice());
        	logger.info("<------Product Details------>"+productmnuf);
        	ProductManufacturerRepository.save(productmnuf);
        	msg="success";
		}
       return msg;  
	}

	@Override
	public List<Map<String, Object>> getAllProduct() {
		List<Map<String,Object>> listData=new LinkedList<Map<String,Object>>();
		List<ProductManufacturer> productlist=ProductManufacturerRepository.findAll();
		logger.info("<------Product Details List------>"+productlist);
		if (null!=productlist && !productlist.isEmpty()) {
			Collections.sort(productlist, (o1,o2)-> o1.getProductcode().compareTo(o2.getProductcode()));
			productlist.parallelStream().forEach(l->{
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("CreatedDate", l.getCreatedDate());
				map.put("ProductId", l.getProductid());
				map.put("ProductName", l.getProductname());
				map.put("ProductCode", l.getProductcode());
				map.put("ProductBrand", l.getProductbrand());
				map.put("ProductMfgDate", l.getProductmfgdate());
				map.put("ProductPrice", l.getProductprice());
				listData.add(map);
			});
		}else {
			throw new NullPointerException("Product Details Not Found");
		}
	  return listData;	
	}

	@Override
	public  List<Map<String, Object>> getProductByProductcode(String productcode) {
		List<Map<String,Object>> listData=new LinkedList<Map<String,Object>>();
		ProductManufacturer product=ProductManufacturerRepository.findByProductcode(productcode);
		logger.info("<------Product Details------>"+product);
		if(null!=product) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("CreatedDate", product.getCreatedDate());
			map.put("ProductId",   product.getProductid());
			map.put("ProductName", product.getProductname());
			map.put("ProductCode", product.getProductcode());
			map.put("ProductBrand",product.getProductbrand());
			map.put("ProductMfgDate",product.getProductmfgdate());
			map.put("ProductPrice", product.getProductprice());
			listData.add(map);
		}else {
			throw new NullPointerException("Product Details Not Found");
		}
		return listData;		
	}

	@Override
	public String updateProductDetailsByProductcode(String productcode,ProductDetailsRequest productDetailsRequest) {
		String msg="";
		ProductManufacturer product=ProductManufacturerRepository.findByProductcode(productcode);
		if(null!=product) {
			product.setProductname(productDetailsRequest.getProductname());
			product.setProductbrand(productDetailsRequest.getProductbrand());
			product.setProductcode(productDetailsRequest.getProductcode());
			product.setProductprice(productDetailsRequest.getProductprice());
			logger.info("<------Product Details------>"+product);
			ProductManufacturerRepository.save(product);
			msg="success";
		}else {
			throw new NullPointerException("Product Not Found To Update Data");
		}
	 return msg;	
	}

	@Override
	public String deleteProductByProductcode(String productcode) {
		String msg="";
		ProductManufacturer product=ProductManufacturerRepository.findByProductcode(productcode);	
		if(null!=product) {
			logger.info("<------Product Details------>"+product);
			ProductManufacturerRepository.delete(product);
			msg="success";
		}else {
			throw new NullPointerException("Product Not Found To Delete Data!!!");
		}
	  return msg;	
	}

}
