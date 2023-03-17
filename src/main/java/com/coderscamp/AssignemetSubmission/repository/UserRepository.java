package com.coderscamp.AssignemetSubmission.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscamp.AssignemetSubmission.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
}