package com.luv2code.ecommerce.dto;

public class ResponseMessage {

	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseMessage(String message) {
		super();
		this.message = message;
	}
	
	
	
	
}
