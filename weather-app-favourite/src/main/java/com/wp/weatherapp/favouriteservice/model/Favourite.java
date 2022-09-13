package com.wp.weatherapp.favouriteservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Favourite")
public class Favourite {

	@Id
	private String id;
	
	private long userId;
	private String city;	
	
	
}
