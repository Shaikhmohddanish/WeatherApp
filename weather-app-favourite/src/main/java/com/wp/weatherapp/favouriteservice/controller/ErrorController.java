package com.wp.weatherapp.favouriteservice.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wp.weatherapp.favouriteservice.dto.ErrorResponseDto;


@RestControllerAdvice
public class ErrorController {

	HttpStatus status;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleOtherExceptions(Exception ex, HttpServletRequest request){
		status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
	}
}
