package com.luv2code.ecommerce.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luv2code.ecommerce.dao.ImageRepository;
import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.entity.ImageModel;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;

import lombok.var;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController
@CrossOrigin 
@RequestMapping(path = "check")
public class ImageUploadController {

	@Autowired
    ImageRepository imageRepository;
	ProductRepository productRepository;

    @PostMapping("/upload")
    public   ImageModel uploadImage( @RequestParam("myFile") MultipartFile file) throws IOException   {

        ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes() );
 
        final ImageModel savedImage = imageRepository.save(img);
        
       
    
       System.out.println("Image saved");
    return savedImage ;
        /*
  			{
	
	"name":"Books",
	"sku":"C++SKU",
	"description":"The new C++11 standard allows programmers to express ideas more clearly, simply, and directly",
	"unitPrice":50,
	"unitsInStock":100,
	"category":"http://localhost:8080/api/product-category/1"  
	 
				}
         * 
         */
     }
    
    @CrossOrigin 
    @GetMapping("/getimages")
    public List<ImageModel> getImages()
    {
    	final List<ImageModel> savedImage = imageRepository.findAll();
		return savedImage;
    }
    
    
    
    @DeleteMapping("deleteallimages")
    public void deleteAllImages()
    {
    	  imageRepository.deleteAllInBatch();
    }
    
}
