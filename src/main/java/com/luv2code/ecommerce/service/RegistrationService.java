package com.luv2code.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.dao.RegistrationRepository;
import com.luv2code.ecommerce.entity.User;

@Service
public class RegistrationService {

	private static final String String = null;
	@Autowired
	private RegistrationRepository repo;
	
	public User saveUser(User user)
	{
		return repo.save(user);
	}
	
	public User fetchUserByEmailId(String email)
	{
		
		return repo.findByEmailId(email);
		}
	
	
	public User fetchUserByEmailIdAndPassword(String email,String password)
	{
		return repo.findByEmailIdAndPassword(email, password);
		
	 
		 
		
	}
}
