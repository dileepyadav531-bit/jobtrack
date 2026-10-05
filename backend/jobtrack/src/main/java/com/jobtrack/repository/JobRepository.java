package com.jobtrack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtrack.entity.Job;

public interface JobRepository extends JpaRepository<Job,Long>{

	
	List<Job> findByTitleContainingIgnoreCaseAndActiveTrue(String title);
	
	List<Job> findByActiveTrue();
	
	Optional<Job> findByIdAndActiveTrue(Long id);
}
