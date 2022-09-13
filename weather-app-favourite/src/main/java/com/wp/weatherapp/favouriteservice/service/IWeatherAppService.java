package com.wp.weatherapp.favouriteservice.service;

import java.util.List;

import com.wp.weatherapp.favouriteservice.exception.CityAlreadyExistsException;
import com.wp.weatherapp.favouriteservice.model.Favourite;

public interface IWeatherAppService {

	public Favourite saveFavourite(Favourite favourite) throws CityAlreadyExistsException;
	
	public List<Favourite> getFavourite(long id);
	
	public boolean deleteFavouriteById(String id);
	
}
