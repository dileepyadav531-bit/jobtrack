package com.jobtrack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jobtrack.dto.AdminApplicationDto;
import com.jobtrack.dto.ApplicationRequestDto;
import com.jobtrack.entity.Application;
import com.jobtrack.entity.ApplicationStatus;
import com.jobtrack.entity.Job;
import com.jobtrack.entity.User;
import com.jobtrack.exception.DuplicateApplicationException;
import com.jobtrack.exception.JobNotAvailableException;
import com.jobtrack.repository.ApplicationRepository;
import com.jobtrack.repository.JobRepository;
import com.jobtrack.repository.UserRepository;

@Service
public class ApplicationService {
	
	private ApplicationRepository applicationRepository;
	private UserRepository userRepository;
	private JobRepository jobRepository;
	
	public ApplicationService(ApplicationRepository applicationRepository,
			UserRepository userRepository,JobRepository jobRepository) {
		this.applicationRepository =applicationRepository;
		this.userRepository=userRepository;
		this.jobRepository=jobRepository;
		
	}
	
	public Application applayForJob(ApplicationRequestDto request) {
		
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		
		String email = authentication.getName();
		
		User user  = userRepository.findByEmail(email).orElseThrow();
		
		Job job = jobRepository.findById(request.getJobId()).orElseThrow();

		if (!job.isActive()) {
		    throw new JobNotAvailableException("Job is no longer available");
		}
		
		Application application = new Application();
		application.setUserId(user.getId());
		application.setJobId(request.getJobId());
		application.setNotes(request.getNotes());
		application.setStatus(ApplicationStatus.APPLIED);
		
boolean alreadyApplied = applicationRepository.existsByUserIdAndJobId(
		application.getUserId(),application.getJobId());

if(alreadyApplied) {
	throw new DuplicateApplicationException("User Already Applied for this job");
}
		
		return applicationRepository.save(application);
	}
	
	public List<AdminApplicationDto> getAllApplications(){
		
		List<Application> applications = applicationRepository.findAll();
		
		List<AdminApplicationDto> result = new ArrayList<>();
		
		for(Application application :applications) {
			
			User user = userRepository.findById(application.getUserId())
			.orElseThrow();
			
			Job job = jobRepository.findById(application.getJobId()).orElseThrow();
			
			
			AdminApplicationDto dto = new AdminApplicationDto();
			
			dto.setApplicationId(application.getId());
			
			dto.setUserId(user.getId());
			dto.setUserName(user.getName());
			dto.setUserEmail(user.getEmail());
			
			dto.setJobId(job.getId());
			dto.setJobTitle(job.getTitle());
			dto.setCompany(job.getCompany());
			
			dto.setStatus(application.getStatus().name());
			
			if(application.getAppliedDate() != null ) {
				dto.setAppliedDate(application.getAppliedDate().toString());
			}
			
			result.add(dto);
		}
		
		
		
		return result;
	}
	
	public List<Application> getMyApplications(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String email = authentication.getName();	
		
		User user = userRepository.findByEmail(email).orElseThrow();
		return applicationRepository.findByUserId(user.getId());
		
	}
	
	
	public Application updateStatus(Long id,String status) {
		
		 Application application = applicationRepository.findById(id).orElseThrow();
		 
		application.setStatus(ApplicationStatus.valueOf(status));
		
		return applicationRepository.save(application);
	}

}
