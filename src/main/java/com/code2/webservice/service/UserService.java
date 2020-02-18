package com.code2.webservice.service;

import java.util.List;

import com.code2.webservice.dto.UserDto;

import javassist.NotFoundException;

public interface UserService {
	public List<UserDto> getUsers();
	public UserDto saveUser(UserDto user);
	public UserDto getUserById(Long id)throws NotFoundException;
	public void deleteUser(Long id);
	public UserDto updateUser(UserDto user) throws NotFoundException;
}
