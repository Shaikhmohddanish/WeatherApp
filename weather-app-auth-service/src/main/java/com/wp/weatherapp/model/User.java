package com.wp.weatherapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "user_db")
@AllArgsConstructor
@NoArgsConstructor
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	
	@Column(unique = true)
	private String useremail;
	
	private String password;
	
	private String Country;
	private String city;
	
	
	
}
