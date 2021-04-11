package com.luv2code.ecommerce;

 
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.luv2code.ecommerce.service.StorageService;
import com.luv2code.ecommerce.dao.CountryRepository;
 

 
 

@SpringBootApplication
 
public class SpringBootEcommerceApplication implements CommandLineRunner{

	@Autowired
	StorageService storageService; 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommerceApplication.class, args);
	}
	
	@Override
	public void run(String... arg) throws Exception {
		//storageService.deleteAll();
		storageService.init();
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
