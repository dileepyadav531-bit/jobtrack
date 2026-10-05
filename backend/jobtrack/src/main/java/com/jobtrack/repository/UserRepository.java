package com.jobtrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtrack.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	public boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
		
	

}
