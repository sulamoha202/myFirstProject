package com.coderscamp.AssignemetSubmission.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Assignment {

	@GeneratedValue(strategy = GenerationType.IDENTITY) @Id
	private Long id;
	private String status;
	private String githubURl;
	private String branch;
	private String codeReviewVideoUrl;
	@ManyToOne(optional=false)
	private User user;
	//TODO: Create private User AssignedTo

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGithubURl() {
		return githubURl;
	}

	public void setGithubURl(String githubURl) {
		this.githubURl = githubURl;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCodeReviewVideoUrl() {
		return codeReviewVideoUrl;
	}

	public void setCodeReviewVideoUrl(String codeReviewVideoUrl) {
		this.codeReviewVideoUrl = codeReviewVideoUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
