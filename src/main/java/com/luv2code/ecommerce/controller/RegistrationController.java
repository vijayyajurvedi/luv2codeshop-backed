package com.luv2code.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@CrossOrigin(origins="*")
	@PostMapping(path="/registeruser")
	public User registerUser(@RequestBody User user) throws Exception
	{
		String tempEmailid=  user.getEmailId();
		
		if(tempEmailid != null && tempEmailid!="")
		{
		  User userObj = registrationService.fetchUserByEmailId(tempEmailid);
		
		  if(userObj!=null)
			  throw new Exception("User with "+tempEmailid+" already eists.");
		}
		
		if(tempEmailid  == null  || tempEmailid=="")
		{
		   
			  throw new Exception("Email Id cant be Blank");
		}
		
		User userObj= null;
		
		userObj= registrationService.saveUser(user);
		return userObj;
	}
	
	@CrossOrigin(origins="*")
	@PostMapping(path="/login")
	public User loginUser(@RequestBody User user) throws Exception
	{
		String tempEmailid=  user.getEmailId();
		String temppassword=  user.getPassword();
		User userObj=null;
		if(tempEmailid!=null && temppassword!=null)
		{
			
			userObj= registrationService.fetchUserByEmailIdAndPassword(tempEmailid, temppassword);
			
		}
		System.out.println(userObj);
		
		if(userObj== null)
		{
			throw new Exception("Bad Credentials");	
			
		}
		return  userObj;
	}
	
}
