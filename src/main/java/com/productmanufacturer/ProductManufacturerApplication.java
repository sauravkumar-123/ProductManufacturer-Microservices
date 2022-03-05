package com.productmanufacturer;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductManufacturerApplication {

	private static final Logger logger=LoggerFactory.getLogger(ProductManufacturerApplication.class);
	
	@Value("${server.port}")
	private String portno;
	
	public static void main(String[] args) {
		logger.info("<---------WareHouse Project Start------------>");
		SpringApplication.run(ProductManufacturerApplication.class, args);
		logger.info("<---------WareHouse Project End------------>");
	}
	
	@PostConstruct
	public void init() {
		logger.info("<---------ProductManufacturer Server Run On PortNo:------------>"+portno);
	}
}
