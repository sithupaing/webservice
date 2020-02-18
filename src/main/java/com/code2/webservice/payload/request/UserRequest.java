package com.code2.webservice.payload.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UserRequest {
	
	@NotEmpty(message="Username is empty")
	private String username;
	
	@NotEmpty(message="Password is empty")
	@Min(message = "Password must be 8", value = 8)
	@Max(message="",value=20)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
