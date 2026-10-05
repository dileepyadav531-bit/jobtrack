package com.jobtrack.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobtrack.dto.LoginRequestDTO;
import com.jobtrack.dto.LoginResponseDTO;
import com.jobtrack.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService=userService;
		
	}
	
	@PostMapping("/login")
	public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		
		return userService.login(loginRequestDTO);
	}

}
