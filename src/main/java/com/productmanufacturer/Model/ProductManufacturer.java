package com.productmanufacturer.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WareHouse_Product")
public class ProductManufacturer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Product_Index")
	private int index_id;
	
	@Column(name = "Warehouse_ProductId", length = 20)
	private String productid;
	
	@Column(name = "Warehouse_ProductName", length = 50)
	private String productname;
	
	@Column(name = "Warehouse_ProductCode", length = 20)
	private String productcode;
	
	@Column(name = "Warehouse_ProductBrand", length = 30)
	private String productbrand;
	
	@Column(name = "Warehouse_ProductMfgDate")
	private Date productmfgdate;
	
	@Column(name = "Warehouse_ProductPrice")
	private double productprice;
	
}
