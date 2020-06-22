package com.code2.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code2.webservice.payload.request.SigninRequest;
import com.code2.webservice.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/admin/login")
	public void loginAdmin(@RequestBody SigninRequest signinRequest) {

	}

	@PostMapping("/user/login")
	public String loginUser(@RequestBody SigninRequest signinRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));

		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			String token = jwtTokenProvider.generateJwtToken(authentication);
			return token;
		}

		return "Authentication Error";

	}

}
