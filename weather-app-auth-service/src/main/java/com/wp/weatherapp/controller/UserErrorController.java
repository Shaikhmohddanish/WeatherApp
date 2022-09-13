package com.wp.weatherapp.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wp.weatherapp.dto.ErrorResponseDto;
import com.wp.weatherapp.exception.InvalidCredentialsException;
import com.wp.weatherapp.exception.InvalidTokenException;
import com.wp.weatherapp.exception.UserAlreadyExistsException;
import com.wp.weatherapp.exception.UserNotExistsException;

@RestControllerAdvice
public class UserErrorController {

	HttpStatus status;
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleAlreadyExceptions(Exception ex, HttpServletRequest request){
		status = HttpStatus.CONFLICT;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
		
		
	}
	
	@ExceptionHandler(UserNotExistsException.class)
	public ResponseEntity<ErrorResponseDto> UserNotFound(Exception ex, HttpServletRequest request){
		status = HttpStatus.NOT_FOUND;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
		
		
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponseDto> InvalidUser(Exception ex, HttpServletRequest request){
		status = HttpStatus.UNAUTHORIZED;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
		
		
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponseDto> InvalidToken(Exception ex, HttpServletRequest request){
		status = HttpStatus.UNAUTHORIZED;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
		
		
	}
	
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
