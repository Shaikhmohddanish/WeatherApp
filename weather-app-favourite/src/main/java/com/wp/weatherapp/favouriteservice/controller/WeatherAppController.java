package com.wp.weatherapp.favouriteservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import com.wp.weatherapp.favouriteservice.exception.CityAlreadyExistsException;
import com.wp.weatherapp.favouriteservice.exception.CityNotFoundException;
import com.wp.weatherapp.favouriteservice.model.Favourite;
import com.wp.weatherapp.favouriteservice.model.ResponseMessage;
import com.wp.weatherapp.favouriteservice.service.IWeatherAppService;

@RestController
@RequestMapping("/watchlists")
public class WeatherAppController {

	@Autowired
	IWeatherAppService service;
	
	@PostMapping
	public ResponseEntity<?> saveFavourite(@RequestBody Favourite favourite){
		ResponseEntity<?> responseEntity;
		try {
			service.saveFavourite(favourite);
			responseEntity = new ResponseEntity<Favourite>(favourite,HttpStatus.CREATED);
		}
		catch(CityAlreadyExistsException e){
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFavourite(@PathVariable String id)
    {
        ResponseEntity<?> responseEntity;
        try
        {
            service.deleteFavouriteById(id);
            responseEntity = new ResponseEntity<ResponseMessage>(new ResponseMessage("City Deleted Successfully"),HttpStatus.OK);
        }
        catch(CityNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }
	
	
	@GetMapping("/list/{id}")
    public ResponseEntity<?> getMyFavouriteCities(@PathVariable long id)
    {
        ResponseEntity<?> responseEntity;
        try
        {
            List<Favourite> favList = service.getFavourite(id);
            responseEntity = new ResponseEntity<List<Favourite>>(favList,HttpStatus.OK);
        }
        catch(CityNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
	
	
}
