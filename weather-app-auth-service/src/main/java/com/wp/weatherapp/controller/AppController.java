package com.wp.weatherapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wp.weatherapp.dto.ResponseDto;

@RestController
@RequestMapping("/app")
public class AppController {

	@GetMapping
	public ResponseDto sayHello(HttpServletRequest m) {
		String msg= (String) m.getAttribute("useremail");
		ResponseDto response = new ResponseDto();
		response.setMessage(msg);
		return response;
	}
	
}