package com.coderscamp.AssignemetSubmission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscamp.AssignemetSubmission.domain.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	
}
