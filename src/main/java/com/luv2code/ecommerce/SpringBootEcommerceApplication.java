package com.luv2code.ecommerce;

 
import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.ecommerce.dao.CountryRepository;
 

 
 

@SpringBootApplication
 
public class SpringBootEcommerceApplication {

	 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommerceApplication.class, args);
	}
 
}

/*
 * 
 * 
INSERT INTO `country` VALUES 
('BR','Brazil'),
('CA','Canada'),
('DE','Germany'),
('IN','India'),
('TR','Turkey'),
('US','United States');


 * 
 * */
