package com.luv2code.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name="user")
@Data
public class User {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private int id;

	    @Column(name="emailId")
	    private String emailId;
	    
	    @Column(name="userName")
	    private String userName;
	    
	    @Column(name="password")
	    private String password;

	     


}
