package com.wp.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wp.weatherapp.dto.AuthToken;
import com.wp.weatherapp.exception.UserAlreadyExistsException;
import com.wp.weatherapp.model.User;
import com.wp.weatherapp.model.UserCredential;
import com.wp.weatherapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("/authenticate")
	public AuthToken authenticateUser(@RequestBody UserCredential credentials) {
		String token = service.loginWithCredentials(credentials);
		return new AuthToken(token);
	}
	
	@PostMapping("/register")
	public User addUser(@RequestBody User user) {
		String tempEmailID = user.getUseremail();
		if(tempEmailID != null && !"".equals(tempEmailID)) {
			User userObj = service.getUserByUseremail(tempEmailID);
			if(userObj != null) {
				throw new UserAlreadyExistsException("User with Email ID "+tempEmailID+" Already exists");
			}
		}
		return service.addUser(user);
	}
	
	@GetMapping("/id/{id}")
	public User getUserById(@PathVariable long id) {
		return service.getUserById(id);
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@GetMapping("/email/{useremail}")
	public User getUserByemail(@PathVariable String useremail) {
		return service.getUserByUseremail(useremail);
	}
	
	
}
