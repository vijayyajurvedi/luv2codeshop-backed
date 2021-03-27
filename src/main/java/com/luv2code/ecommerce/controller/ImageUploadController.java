package com.luv2code.ecommerce.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luv2code.ecommerce.dao.ImageRepository;
import com.luv2code.ecommerce.entity.ImageModel;

@RestController
@CrossOrigin(origins = "*")
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
}
