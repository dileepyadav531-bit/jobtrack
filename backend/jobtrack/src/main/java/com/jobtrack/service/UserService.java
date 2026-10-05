package com.jobtrack.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobtrack.dto.LoginRequestDTO;
import com.jobtrack.dto.LoginResponseDTO;
import com.jobtrack.dto.RegistrationResponseDto;
import com.jobtrack.dto.UpdateUserProfileDto;
import com.jobtrack.dto.UserProfileDto;
import com.jobtrack.dto.UserRegistrationDTO;
import com.jobtrack.entity.User;
import com.jobtrack.exception.EmailAlreadyExitException;
import com.jobtrack.exception.InvalidCredentialsException;
import com.jobtrack.repository.UserRepository;
import com.jobtrack.security.JwtService;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	private JwtService jwtService;
	
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService) {
		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		
	}
	
	public RegistrationResponseDto saveUser(UserRegistrationDTO userRegistrationDTO) {
		
		

		if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
		  
		    throw new EmailAlreadyExitException("Email already exists");
		}

		
		
		User user = new User();
		user.setName(userRegistrationDTO.getName());
		user.setEmail(userRegistrationDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
		user.setPhone(userRegistrationDTO.getPhone());
		user.setRole("User");
		
		User savedUser = userRepository.save(user);
		
		RegistrationResponseDto response = new RegistrationResponseDto();
		
		response.setId(savedUser.getId());
		response.setName(savedUser.getName());
		response.setEmail(savedUser.getEmail());
		response.setPhone(savedUser.getPhone());
		response.setRole(savedUser.getRole());
		
		return response;
	}
	
	
	public LoginResponseDTO login(LoginRequestDTO loginRequestDto) {
		
	Optional<User> useroptional = userRepository.findByEmail(loginRequestDto.getEmail());
	
	if(useroptional.isEmpty()) {
		throw new InvalidCredentialsException("Invalid email or password");
	}
	
	User user = useroptional.get();
	
	if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
		
		throw new InvalidCredentialsException("Invalid email or password ");
	}
	
	String token = jwtService.generateToken(user);
	
	LoginResponseDTO loginResponseDto = new LoginResponseDTO();
	
	loginResponseDto.setId(user.getId());
	loginResponseDto.setName(user.getName());
	loginResponseDto.setEmail(user.getEmail());
	loginResponseDto.setRole(user.getRole());
	loginResponseDto.setToken(token);
		
		return loginResponseDto;
	}
	
	public UserProfileDto getProfile(){
		
Authentication authentication  =  SecurityContextHolder.getContext().getAuthentication();
		
		String email =authentication.getName();
		
		User user = userRepository.findByEmail(email).orElseThrow();
		
		UserProfileDto profile = new UserProfileDto();
		profile.setName(user.getName());
		profile.setEmail(user.getEmail());
		profile.setPhone(user.getPhone());
		
		return profile;
		
	}
	
	public UserProfileDto updateProfile(UpdateUserProfileDto profile) {
		
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		
		String email = authentication.getName();
		
		User user = userRepository.findByEmail(email).orElseThrow();
		
		user.setName(profile.getName());
		user.setPhone(profile.getPhone());
		userRepository.save(user);
		
		
		UserProfileDto response = new UserProfileDto();
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setPhone(user.getPhone());
		
		return response ;
		
		
	}

}
