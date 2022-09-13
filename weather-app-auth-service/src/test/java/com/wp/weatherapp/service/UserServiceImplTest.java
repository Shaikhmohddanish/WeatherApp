package com.wp.weatherapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wp.weatherapp.model.User;
import com.wp.weatherapp.repository.UserRepository;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

	@Mock
	UserRepository repo;
	
	@InjectMocks
	UserServiceImpl service;
	

	User user = new User(1111L,"Danish","Danish@gmail.com","admin","IN","Thane");
	
	@Test
	void testAddUser() {
		when(repo.save(user)).thenReturn(user);
		assertEquals(user,service.addUser(user));
	}

	@Test
	void testGetUserById() {
		when(repo.findById(user.getId())).thenReturn(Optional.of(user));
		assertEquals(user, service.getUserById(1111L));
	}

	@Test
	void testUpdateUser() {
		User newUser = new User(1111L,"Danish","Danish123@gmail.com","admin","IN","Thane");
		when(repo.save(newUser)).thenReturn(newUser);
		assertEquals(newUser,service.updateUser(newUser));
	}


	@Test
	void testGetUserByUseremail() {
		when(repo.findByUseremail(user.getUseremail())).thenReturn(user);
		assertEquals(user, service.getUserByUseremail("Danish@gmail.com"));
	}


}
