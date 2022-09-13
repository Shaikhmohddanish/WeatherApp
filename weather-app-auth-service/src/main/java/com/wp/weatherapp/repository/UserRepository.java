package com.wp.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wp.weatherapp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUseremail(String useremail);
	
	public boolean existsByUseremailAndPassword(String useremail,String password);
	
}
