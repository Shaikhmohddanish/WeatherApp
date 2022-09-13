package com.wp.weatherapp.favouriteservice.exception;

public class CityAlreadyExistsException extends RuntimeException{

	public CityAlreadyExistsException(String messaga) {
		super(messaga);
	}
}
