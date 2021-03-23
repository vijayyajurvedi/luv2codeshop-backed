package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.luv2code.ecommerce.entity.User;

public interface RegistrationRepository  extends JpaRepository<User, Integer> {

	public User findByEmailId(String email);
	public User findByEmailIdAndPassword(String email,String password);

}
