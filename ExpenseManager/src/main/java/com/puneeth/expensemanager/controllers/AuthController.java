package com.puneeth.expensemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.puneeth.expensemanager.Dto.UserDto;
import com.puneeth.expensemanager.entities.AuthModel;
import com.puneeth.expensemanager.entities.User;
import com.puneeth.expensemanager.services.UserService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	@Autowired
	public UserService userService;

	@Autowired
	public AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<HttpStatus> login(@RequestBody AuthModel authModel){
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return  new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@Valid @RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}
}
