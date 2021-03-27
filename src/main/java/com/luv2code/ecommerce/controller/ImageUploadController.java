package com.luv2code.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luv2code.ecommerce.dao.ImageRepository;
import com.luv2code.ecommerce.entity.ImageModel;

@RestController
@CrossOrigin 
@RequestMapping(path = "check")
public class ImageUploadController {

	@Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload")
    public ImageModel uploadImage(@RequestParam("myFile") MultipartFile file) throws IOException {

        ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes() );


        final ImageModel savedImage = imageRepository.save(img);


        System.out.println("Image saved");


        return savedImage;


    }
    
    @CrossOrigin 
    @GetMapping("/getimages")
    public List<ImageModel> getImages()
    {
    	final List<ImageModel> savedImage = imageRepository.findAll();
		return savedImage;
    }
    
    
}
