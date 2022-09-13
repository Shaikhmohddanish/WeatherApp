package com.wp.weatherapp.favouriteservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.wp.weatherapp.favouriteservice.exception.CityAlreadyExistsException;
import com.wp.weatherapp.favouriteservice.exception.CityNotFoundException;
import com.wp.weatherapp.favouriteservice.model.Favourite;
import com.wp.weatherapp.favouriteservice.repository.WeatherAppRepo;

@Service
public class WeatherAppImpl implements IWeatherAppService{

	@Autowired
	WeatherAppRepo repo;
	
	@Override
	public boolean deleteFavouriteById(String id) {
		Favourite favourite = repo.findById(id).orElse(null);
		if(favourite==null) {
			throw new CityNotFoundException("City Not Found");
		}
		repo.delete(favourite);
		return true;
	}
	
	@Override
	public List<Favourite> getFavourite(long id) {
		List<Favourite>  favList =  repo.findByUserId(id);
		if(favList.size()==0) {
			throw new CityNotFoundException("No city Found");
		}
		return favList;
	}
	
	@Override
	public Favourite saveFavourite(Favourite favourite) throws CityAlreadyExistsException {
		Optional<Favourite> checkFavourite = repo.findByUserIdAndCity(favourite.getUserId(), favourite.getCity());
		
		if(checkFavourite.isPresent()) {
			throw new CityAlreadyExistsException("City Already Exist in Favourite List");
		}
		return repo.save(favourite);
	}
	
}
