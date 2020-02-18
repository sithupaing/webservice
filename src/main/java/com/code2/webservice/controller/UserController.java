package com.code2.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code2.webservice.dto.UserDto;
import com.code2.webservice.payload.request.UserRequest;
import com.code2.webservice.payload.response.ApiResponse;
import com.code2.webservice.payload.response.UserResponse;
import com.code2.webservice.service.UserService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@CrossOrigin(origins = "http://localhost:8081")
	public UserResponse createUser(@Valid @RequestBody UserRequest userRequest,BindingResult result) {
		
//		if(userRequest.getUsername().isEmpty()) {
//			throw new MissingRequiredFieldException("Username is empty");
//		}
		
//		if(result.hasErrors()) {
//			String [] message = "";
//			for(ObjectError error:result.getAllErrors()) {
//			    message += error.getDefaultMessage()+"\n";
//			}
//			throw new MissingRequiredFieldException(message);
//		}
		
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userRequest, user);
		UserDto savedUserDto =  userService.saveUser(user);
		UserResponse savedUserResponse = new UserResponse();
		BeanUtils.copyProperties(savedUserDto, savedUserResponse);
		return savedUserResponse;
	}
	
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:8081")
	public UserResponse getUser(@PathVariable("id")Long id) throws NotFoundException {
		UserDto userDto = userService.getUserById(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return userResponse;
	}
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:8081")
	public List<UserResponse> getUsers() {
		
		List<UserDto> userDtoList = userService.getUsers();
		List<UserResponse> userResponseList = new ArrayList<UserResponse>();
		
		for(UserDto userDto:userDtoList) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDto, userResponse);
			userResponseList.add(userResponse);
		}
		
		return userResponseList;
	}
	
	
	@PutMapping("/{id}")
	public UserResponse updateUser(@PathVariable("id")Long id,@RequestBody UserRequest userRequest) throws NotFoundException {
		UserDto userDto = new UserDto();
		userDto.setId(id);
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto updatedUserDto = userService.updateUser(userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updatedUserDto, userResponse);
		return userResponse; 
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:8081")
	public ApiResponse deleteUser(@PathVariable("id")Long id) {
		userService.deleteUser(id);
		return new ApiResponse(true, "Successfully deleted.");
	}

}
