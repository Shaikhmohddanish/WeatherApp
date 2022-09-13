package com.wp.weatherapp.controller;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wp.weatherapp.model.User;
import com.wp.weatherapp.service.UserService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
class UserControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserService service;
	
	User user = new User(1111L,"Danish","Danish@gmail.com","admin","IN","Thane");
	
	@Test
	void testAddUser() throws Exception {
		when(service.addUser(user)).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
				.contentType("application/json")
				.content("{\"id\":1111,\"username\":\"Danish\",\"useremail\":\"Danish@gmail.com\",\"password\":\"admin\",\"Country\":\"IN\",\"city\":\"Thane\"}");
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void testGetUserById() throws Exception {
		when(service.getUserById(user.getId())).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/id/1111")
				.contentType("application/json")
				.content("{\"id\":1111,\"username\":\"Danish\",\"useremail\":\"Danish@gmail.com\",\"password\":\"admin\",\"Country\":\"IN\",\"city\":\"Thane\"}");
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void testUpdateUser() throws Exception {
		User newUser = new User(1111L,"Danish","Danish123@gmail.com","admin","IN","Thane");
		when(service.getUserById(newUser.getId())).thenReturn(newUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/id/1111")
				.contentType("application/json")
				.content("{\"id\":1111,\"username\":\"Danish\",\"useremail\":\"Danish123@gmail.com\",\"password\":\"admin\",\"Country\":\"IN\",\"city\":\"Thane\"}");
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
	}


	@Test
	void testGetUserByemail() throws Exception {
		when(service.getUserByUseremail(user.getUseremail())).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/email/Danish@gmail.com")
				.contentType("application/json")
				.content("{\"id\":1111,\"username\":\"Danish\",\"useremail\":\"Danish@gmail.com\",\"password\":\"admin\",\"Country\":\"IN\",\"city\":\"Thane\"}");
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
	}



}
