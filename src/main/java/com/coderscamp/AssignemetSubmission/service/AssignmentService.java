package com.coderscamp.AssignemetSubmission.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscamp.AssignemetSubmission.domain.Assignment;
import com.coderscamp.AssignemetSubmission.domain.User;
import com.coderscamp.AssignemetSubmission.repository.AssignmentRepository;

@Service
public class AssignmentService {
	
		@Autowired
		private AssignmentRepository assignmentRepo;
		
	public Assignment save(User user) {	
		Assignment assignment = new Assignment();
		assignment.setStatus("Needs to be Submited");
		assignment.setUser(user);
		
		return assignmentRepo.save(assignment);
	}

}
