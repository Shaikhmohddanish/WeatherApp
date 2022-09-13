package com.wp.weatherapp.favouriteservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wp.weatherapp.favouriteservice.exception.CityAlreadyExistsException;
import com.wp.weatherapp.favouriteservice.exception.CityNotFoundException;
import com.wp.weatherapp.favouriteservice.model.Favourite;
import com.wp.weatherapp.favouriteservice.repository.WeatherAppRepo;

@ExtendWith(SpringExtension.class)
class WeatherAppImplTest {

	
	@Mock
	WeatherAppRepo repo;
	
	@InjectMocks
	WeatherAppImpl service;
	
	Favourite fav = new Favourite("testid123",11,"mumbai");
	
	@Test
	void testDeleteFavouriteById() {
		when(repo.findById(fav.getId())).thenReturn(Optional.of(fav));
		assertTrue(service.deleteFavouriteById("testid123"));
	}
	
	@Test
	void testDeleteFavouriteByIdFailure() {
		when(repo.findById("test124")).thenReturn(Optional.of(fav));
		assertThrows(CityNotFoundException.class,()->{
			service.deleteFavouriteById(fav.getId());
		});
	}
	

	@Test
	void testGetFavourite() {
		List<Favourite> favList = repo.findByUserId(11);
		when(repo.findByUserId(fav.getUserId())).thenReturn(favList);
		assertNotNull(favList);
	}
	
	@Test
	void testGetFavouriteFailure() {
		List<Favourite> favList = repo.findByUserId(10000L);
		when(repo.findByUserId(fav.getUserId())).thenReturn(favList);
		assertThrows(CityNotFoundException.class, ()->{
			service.getFavourite(fav.getUserId());
		});
	}
	

	@Test()
	void testSaveFavourite() {
		when(repo.save(fav)).thenReturn(fav);
		assertEquals(fav,service.saveFavourite(fav));
	}
	
	@Test()
	void testSaveFavouriteFailure() {
		when(repo.findByUserIdAndCity(11, "mumbai")).thenReturn(Optional.of(fav));
		when(repo.save(fav)).thenReturn(fav);
		assertThrows(CityAlreadyExistsException.class, ()->{
			service.saveFavourite(fav);
		});
	}

}
