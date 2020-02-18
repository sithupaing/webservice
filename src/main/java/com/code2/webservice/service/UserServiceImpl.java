package com.code2.webservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code2.webservice.dto.UserDto;
import com.code2.webservice.entity.User;
import com.code2.webservice.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDto> getUsers(){
		
		List<User> users= userRepository.findAll();
		
		List<UserDto> userResponseList = new ArrayList<>();
		
		for(User tmpUser:users) {
			UserDto userResponse = new UserDto();
			BeanUtils.copyProperties(tmpUser, userResponse);
			userResponseList.add(userResponse);
		}
		
		return userResponseList;
	}

	@Override
	public UserDto saveUser(UserDto userRequest) {
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);
		User savedUser = userRepository.save(user);
		UserDto userResponse = new UserDto();
		BeanUtils.copyProperties(savedUser, userResponse);
		return userResponse;
	}

	@Override
	public UserDto getUserById(Long id) throws NotFoundException {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(optional.get(), userDto);
			return userDto;
		}
		throw new NotFoundException("User not found");
		
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDto updateUser(UserDto userDto) throws NotFoundException {
		User user = userRepository.getOne(userDto.getId());
		
		if(user!=null) {
			BeanUtils.copyProperties(userDto, user);
			User updatedUser = userRepository.save(user);
			UserDto updatedUserDto = new UserDto();
			BeanUtils.copyProperties(updatedUser, updatedUserDto);
			return updatedUserDto;
		}
		
		throw new NotFoundException("User not found");
	}

}
