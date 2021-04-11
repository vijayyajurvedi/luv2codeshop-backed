package com.luv2code.ecommerce.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public void init();
	
	public void save(MultipartFile file);
	
	public Resource load(String filename);
	
	public Stream<Path> loadall() throws IOException;
	
	public void deleteAll();
}
