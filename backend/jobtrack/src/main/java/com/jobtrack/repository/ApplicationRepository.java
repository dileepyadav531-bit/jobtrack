package com.jobtrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtrack.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application,Long>{

	
	List<Application> findByUserId(Long userId);
	
	boolean existsByUserIdAndJobId(Long userId,Long jobId);
}
