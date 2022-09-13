package com.wp.weatherapp.favouriteservice.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wp.weatherapp.favouriteservice.model.Favourite;

public interface WeatherAppRepo extends MongoRepository<Favourite, String>{
	
	public List<Favourite> findByUserId(long userId);
	
	Optional<Favourite> findByUserIdAndCity(long id,String city);
	
}
