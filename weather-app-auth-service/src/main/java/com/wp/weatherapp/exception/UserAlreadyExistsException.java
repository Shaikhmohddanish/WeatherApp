package com.wp.weatherapp.exception;


public class UserAlreadyExistsException extends RuntimeException{

	public UserAlreadyExistsException(String message) {
		super(message);
	}
	
}
