package com.code2.webservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.code2.webservice.dto.UserDto;
import com.code2.webservice.entity.User;
import com.code2.webservice.repository.UserRepository;

class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	void setUp() throws Exception {
			MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetUsers() {
		
		List<User> userList = new ArrayList<>();
		userList.add(new User());
		userList.add(new User());
		when(userRepository.findAll()).thenReturn(userList);
		
		List<UserDto> users = userService.getUsers();
		
		assertEquals(users.size(), 2);
		
	}

}
