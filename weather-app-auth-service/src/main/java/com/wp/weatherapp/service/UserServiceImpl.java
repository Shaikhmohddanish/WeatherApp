package com.wp.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wp.weatherapp.exception.InvalidCredentialsException;
import com.wp.weatherapp.exception.UserNotExistsException;
import com.wp.weatherapp.model.User;
import com.wp.weatherapp.model.UserCredential;
import com.wp.weatherapp.repository.UserRepository;
import com.wp.weatherapp.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public User addUser(User user) {
		return repo.save(user);
	}

	@Override
	public User getUserById(long id) {
		return repo.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		return repo.save(user);
	}

	@Override
	public User getUserByUseremail(String useremail) {
		return repo.findByUseremail(useremail);
	}
	
	@Override
	public String loginWithCredentials(UserCredential creds) {
		boolean valid = repo.existsByUseremailAndPassword(creds.getUseremail(), creds.getPassword());
		if(!valid) {
			throw new InvalidCredentialsException("Incorrect Useremail or Password");
		}
		return jwtUtil.generateToken(creds.getUseremail());
	}
	
	@Override
	public User authorizeWithToken(String token) {
		
		String useremail = jwtUtil.decodeToken(token);
		User user = repo.findByUseremail(useremail);
		if(user == null ) {
			throw new UserNotExistsException("User with Email ID "+useremail+" Does not Exists");
		}
		return user;
		
	}
	

}
