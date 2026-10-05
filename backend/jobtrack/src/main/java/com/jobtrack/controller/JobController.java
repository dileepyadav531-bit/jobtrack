package com.jobtrack.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtrack.entity.Job;
import com.jobtrack.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
	
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		
		this.jobService = jobService;
	}
	
	
	@PostMapping
	public Job createJob(@RequestBody Job job) {
		
		
		return jobService.saveJob(job);
	}
	
	@GetMapping
	public List<Job> getAllJobs(){
		
		
		return jobService.getAllJobs();
		
	}
	
	@GetMapping("/{id}")
	public Optional<Job> getJobById(@PathVariable Long id){
		
		return jobService.getJobById(id);
		
	
	}
	
	@PutMapping("/{id}")
	public Job updateJob(@PathVariable Long id,@RequestBody Job job) {
		
		return jobService.updateJob(id,job);  
	}
	
	@DeleteMapping("/{id}")
	public String deleteJob(@PathVariable Long id) {
		
		jobService.deleteJob(id);
		
		return "delete successfully";
	}
	
	
	@GetMapping("/search")
	public List<Job> searchJob(@RequestParam String title){
		return jobService.searchJobs(title);
	}
	
	

}
