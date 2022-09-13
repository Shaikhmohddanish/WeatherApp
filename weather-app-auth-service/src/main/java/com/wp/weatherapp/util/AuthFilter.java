package com.wp.weatherapp.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.wp.weatherapp.exception.InvalidCredentialsException;
import com.wp.weatherapp.exception.InvalidTokenException;
import com.wp.weatherapp.model.User;
import com.wp.weatherapp.service.UserService;



@Component
public class AuthFilter implements Filter {

	@Autowired
	private UserService service;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if(httpRequest.getRequestURI().startsWith("/users")) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		String token = httpRequest.getHeader("token");
		try {
		User user = service.authorizeWithToken(token);
		httpRequest.setAttribute("useremail", user.getUseremail());
		chain.doFilter(httpRequest, httpResponse);
		return;
		}
		catch (InvalidTokenException | InvalidCredentialsException ex) {
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		
		
		
	}

}