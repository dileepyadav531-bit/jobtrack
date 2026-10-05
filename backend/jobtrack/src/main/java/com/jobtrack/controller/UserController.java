package com.jobtrack.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobtrack.dto.RegistrationResponseDto;
import com.jobtrack.dto.UpdateUserProfileDto;
import com.jobtrack.dto.UserProfileDto;
import com.jobtrack.dto.UserRegistrationDTO;

import com.jobtrack.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService){
		this.userService=userService;
	}
	
	@PostMapping
	public RegistrationResponseDto createUsers(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
		
		return userService.saveUser(userRegistrationDTO);
	}
	
	@GetMapping("/profile")
	public UserProfileDto getProfile() {
		
		
		
		return userService.getProfile();
	}
	
	@PutMapping("/profile")
	public UserProfileDto updateProfile(@RequestBody UpdateUserProfileDto profile) {
		
		return userService.updateProfile(profile);
	}

}
