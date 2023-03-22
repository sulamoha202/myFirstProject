package com.coderscamp.AssignemetSubmission.web;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscamp.AssignemetSubmission.domain.Assignment;
import com.coderscamp.AssignemetSubmission.domain.User;
import com.coderscamp.AssignemetSubmission.service.AssignmentService;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
	
	@Autowired
	private AssignmentService assignmentService;
	
	@PostMapping("")
	public ResponseEntity<?> createAssignment(@AuthenticationPrincipal User user){
		Assignment newAssignment = assignmentService.save(user);
		
		return ResponseEntity.ok(newAssignment);
	}
	
	@GetMapping("{assignmentId}")
	public ResponseEntity<?> getAssignment(@PathVariable Long assignmentId,@AuthenticationPrincipal User user){
		Optional<Assignment> assignedOtp= assignmentService.findById(assignmentId);
	 return ResponseEntity.ok(assignedOtp.orElse(new Assignment()));
	}
	
	@GetMapping("")
    public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user) {
        Set<Assignment> assignmentsByUser = assignmentService.findByUser(user);
        return ResponseEntity.ok(assignmentsByUser);
    }
}
