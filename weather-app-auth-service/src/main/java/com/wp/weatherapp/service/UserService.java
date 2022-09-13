package com.wp.weatherapp.service;


import com.wp.weatherapp.model.User;
import com.wp.weatherapp.model.UserCredential;

public interface UserService {

	public User addUser(User user);
	
	public User getUserById(long id);
	
	public User getUserByUseremail(String useremail);
	
	public User updateUser(User user);
	
	public String loginWithCredentials(UserCredential creds);
	
	public User authorizeWithToken(String token);
}
