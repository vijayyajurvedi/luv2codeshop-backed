package com.luv2code.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.luv2code.ecommerce.service.StorageService;
import com.luv2code.ecommerce.dto.FileInfo;
import com.luv2code.ecommerce.dto.ResponseMessage;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FileUploadController {

	@Autowired
	StorageService storageService;

	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		storageService.deleteAll();

		return "All Files Deleted";
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.save(file);

			message = file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			// TODO: handle exception
			message = "File " + file.getOriginalFilename() + " Uploaded Sucessfully";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@PostMapping("/uploads")
	public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		String message = "";
		try {
			List<String> fileNames = new ArrayList<>();

			Arrays.asList(files).stream().forEach(file -> {
				storageService.save(file);
				fileNames.add(file.getOriginalFilename());
			});

			message = "Uploaded the files successfully: " + fileNames;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Fail to upload files!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getlistfiles() throws IOException {

		List<FileInfo> fileinfos = storageService.loadall().map(

				path -> {
					String filename = path.getFileName().toString();
					String url = MvcUriComponentsBuilder
							.fromMethodName(FileUploadController.class, "getfile", path.getFileName().toString())
							.build().toString();

					return new FileInfo(filename, url);
				}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileinfos);

	}

	@GetMapping("/filelink/{filenm:.+}")
	public ResponseEntity<FileInfo> getlistfile(@PathVariable String filenm) throws IOException {

		List<FileInfo> fileinfos = storageService.loadall().map(

				path -> {
					String filename = path.getFileName().toString();
					String url = MvcUriComponentsBuilder
							.fromMethodName(FileUploadController.class, "getfile", path.getFileName().toString())
							.build().toString();

					return new FileInfo(filename, url);
				}).collect(Collectors.toList());

		 
		FileInfo fileinfo = fileinfos.stream().filter(p -> p.getName().equals( filenm)).findFirst().orElse(null);
		 
		return ResponseEntity.status(HttpStatus.OK).body(fileinfo);

	}

	@GetMapping("/file/{filename:.+}")
	public ResponseEntity<Resource> getfile(@PathVariable String filename) {

		Resource file = storageService.load(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
