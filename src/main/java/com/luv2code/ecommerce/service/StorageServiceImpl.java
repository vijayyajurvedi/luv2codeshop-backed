package com.luv2code.ecommerce.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

 

@Service
public class StorageServiceImpl implements StorageService {

	Path root = Paths.get("products");

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(!Files.exists(this.root ))
		{
			try {
				Files.createDirectory(this.root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void save(MultipartFile file) {
		// TODO Auto-generated method stub

		if (file.isEmpty()) {
			throw new RuntimeException("Failed to store empty file.");
		}
		
		try {

			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Couldnt Store File " + e.getMessage());
		}

	}

	@Override
	  public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	@Override
	public Stream<Path> loadall() throws IOException {
		// TODO Auto-generated method stub
 	 
		  return Files.walk(this.root, 1)
			        .filter(path -> !path.equals(this.root))
			        .map(path -> this.root.relativize(path));
		  
		  //This return files in array below 
		  //["file:///C:/Users/CHIU/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/FileUploadPooja/Shwetali_Yajurvedi.jpeg",
		  //"file:///C:/Users/CHIU/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/FileUploadPooja/Vijay_Yajurvedi.jpg"]
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		 FileSystemUtils.deleteRecursively(root.toFile());
	}

}
