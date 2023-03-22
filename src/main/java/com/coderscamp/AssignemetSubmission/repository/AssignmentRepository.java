package com.coderscamp.AssignemetSubmission.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscamp.AssignemetSubmission.domain.Assignment;
import com.coderscamp.AssignemetSubmission.domain.User;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	
	Set<Assignment> findByUser(User user);
}
