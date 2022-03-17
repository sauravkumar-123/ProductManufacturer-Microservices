package com.productmanufacturer;

import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

@SpringBootApplication
@EnableEurekaClient
public class ProductManufacturerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ProductManufacturerApplication.class);

//	private static final String dateFormat="dd-MM-yyyy";
	private static final String dateTimeFormat = "dd-MM-yyyy HH:mm:ss";

	@Value("${server.port}")
	private String portno;

	public static void main(String[] args) {
		logger.info("<---------ProjectManufacturer Start------------>");
		SpringApplication.run(ProductManufacturerApplication.class, args);
		logger.info("<---------ProjectManufacturer End------------>");
	}

	@PostConstruct
	public void init() {
		logger.info("<---------ProductManufacturer Server Run On PortNo:------------>" + portno);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> {
			builder.simpleDateFormat(dateTimeFormat);
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			builder.serializers(new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
		};
	}
}
