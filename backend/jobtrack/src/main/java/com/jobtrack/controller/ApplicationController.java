package com.jobtrack.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtrack.dto.AdminApplicationDto;
import com.jobtrack.dto.ApplicationRequestDto;
import com.jobtrack.entity.Application;
import com.jobtrack.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
	
	private ApplicationService applicationService;
	
	public ApplicationController(ApplicationService applicationService ){
		this.applicationService = applicationService;
	}
	
	@PostMapping
	public Application applayForJob(@RequestBody ApplicationRequestDto request) {
		
		return applicationService.applayForJob(request);
	}
	
	
	@GetMapping
	public List<AdminApplicationDto> getAllApplications(){
		
		return applicationService.getAllApplications();
	}
	
	@GetMapping("/my")
	public List<Application> getMyApplications() {
		
		return applicationService.getMyApplications();
	}
	
	@PutMapping("/{id}/status")
	public Application updateStatus(@PathVariable Long id,@RequestParam String status) {
		
		
		return applicationService.updateStatus(id,status);
		
	}
	
	

}
