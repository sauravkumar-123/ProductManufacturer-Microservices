package com.productmanufacturer.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.productmanufacturer.Model.ProductManufacturer;

@Repository
public interface ProductManufacturerRepository extends JpaRepository<ProductManufacturer, String> {

	public boolean existsByProductCodeOrSerialNoAndIsActive(@Param(value = "productcode") String productcode,
			@Param(value = "serialNo") String serialNo, @Param(value = "isActive") char isActive);

	@Query(value = "SELECT p FROM ProductManufacturer p WHERE p.isActive =:isActive")
	public List<ProductManufacturer> findAll(@Param(value = "isActive") char isActive);

	@Query(value = "SELECT p FROM ProductManufacturer p WHERE p.productCode =:productcode AND p.isActive=:isActive")
	public Optional<ProductManufacturer> findByProductCodeAndIsActive(@Param(value = "productcode") String productcode,
			@Param(value = "isActive") char isActive);

	@Query(value = "SELECT p FROM ProductManufacturer p WHERE ( ( p.productCode =:searchKey OR UPPER(p.productName) LIKE concat('%', UPPER(:searchKey), '%') ) OR ( UPPER(p.productbrandName) LIKE concat('%', UPPER(:searchKey), '%') OR p.modelNo =:searchKey ) OR ( p.serialNo =:searchKey ) ) AND p.isActive=:isActive")
	public List<ProductManufacturer> findBySearchkey(@Param(value = "searchKey") String searchKey,
			@Param(value = "isActive") char isActive);

}
