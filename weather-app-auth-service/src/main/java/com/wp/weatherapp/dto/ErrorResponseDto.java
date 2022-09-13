package com.wp.weatherapp.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	
}
