package com.productmanufacturer.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.productmanufacturer.Model.ProductManufacturer;

@Repository
public interface ProductManufacturerRepository extends JpaRepository<ProductManufacturer, Integer>{

	@Query("SELECT p FROM ProductManufacturer p WHERE p.productid =:productid OR p.productcode =:productcode")
	public ProductManufacturer findByProductidOrProductcode(@Param(value = "productid") String productid,@Param(value = "productcode") String productcode);
	@Query("SELECT p FROM ProductManufacturer p WHERE p.productcode =:productcode")
	public ProductManufacturer findByProductcode(@Param(value = "productcode") String productcode);
}
