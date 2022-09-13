package com.wp.weatherapp.favouriteservice.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import com.wp.weatherapp.favouriteservice.model.Favourite;
import com.wp.weatherapp.favouriteservice.service.IWeatherAppService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value=WeatherAppController.class)
class WeatherAppControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	IWeatherAppService service;
	
	Favourite fav = new Favourite("testid123",11,"mumbai");
	
	
	@Test
	void testSaveFavourite() throws Exception {
		when(service.saveFavourite(fav)).thenReturn(fav);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/watchlists")
						.contentType("application/json")
						.content("{\"id\":\"testid123\",\"userId\":11,\"city\":\"mumbai\"}");
		mockMvc.perform(requestBuilder)
		.andExpect(status().isCreated())
		.andReturn();
	}

	@Test
	void testDeleteFavourite() throws Exception {
		when(service.deleteFavouriteById(fav.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/watchlists/delete/testid123")
				.content("City Deleted Successfully");
		mockMvc.perform(requestBuilder)
		 .andExpect(status().isOk())
		 .andReturn();
	}

	@Test
	void testGetMyFavouriteCities() throws Exception {
		List<Favourite> favList = new ArrayList<>();
		favList.add(fav);
		when(service.getFavourite(fav.getUserId())).thenReturn(favList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/watchlists/list/11")
				.contentType("application/json")
				.content("[{\"id\":\"testid123\",\"userId\":11,\"city\":\"mumbai\"}]");
		mockMvc.perform(requestBuilder)
		 .andExpect(status().isOk())
		 .andReturn();
	}

}
