package com.jobtrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobtrack.entity.Job;
import com.jobtrack.repository.JobRepository;



@Service
public class JobService {
	
	private JobRepository jobRepository;
	
	
	public JobService(JobRepository jobRepository) {
		
		this.jobRepository = jobRepository;
		
	}
	
	public Job saveJob(Job job) {
		
		
		return jobRepository.save(job);
	}
	
	public List<Job> getAllJobs(){
		
		return jobRepository.findByActiveTrue();
	}
	
	
	public List<Job> searchJobs(String title){
		return jobRepository.findByTitleContainingIgnoreCaseAndActiveTrue(title);
	}
	
	
	public Optional<Job> getJobById(Long id){
		
		return jobRepository.findByIdAndActiveTrue(id);
	}
	
	public Job updateJob(Long id,Job job) {
		
		Job  existingJob = jobRepository.findById(id).orElseThrow();
		
		existingJob.setTitle(job.getTitle());
		existingJob.setCompany(job.getCompany());
		existingJob.setLocation(job.getLocation());
		existingJob.setJobType(job.getJobType());
		existingJob.setDescription(job.getDescription());
		existingJob.setSalary(job.getSalary());
		existingJob.setExperience(job.getExperience());
		existingJob.setSkills(job.getSkills());
		
		
		return jobRepository.save(existingJob);
		
	}
	
	
	public void deleteJob(Long id) {
		
		Job job = jobRepository.findById(id).orElseThrow();
		
		job.setActive(false);
		
		jobRepository.save(job);
	}
	
	

}
