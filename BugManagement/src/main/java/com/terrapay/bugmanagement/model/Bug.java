package com.terrapay.bugmanagement.model;

public class Bug {
	private int id;

	private String email;
	private String priority;
	private String status;
	private String projectOwner;
	private String creationDate;
	private String completionDate;
	private String description;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProjectOwner() {
		return projectOwner;
	}
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Bug(String email, String priority, String status, String projectOwner, String creationDate,
			String completionDate, String description) {
		super();
		this.email = email;
		this.priority = priority;
		this.status = status;
		this.projectOwner = projectOwner;
		this.creationDate = creationDate;
		this.completionDate = completionDate;
		this.description = description;
	}
	public Bug(int id, String email, String priority, String status, String projectOwner, String creationDate,
			String completionDate, String description) {
		super();
		this.id = id;
		this.email = email;
		this.priority = priority;
		this.status = status;
		this.projectOwner = projectOwner;
		this.creationDate = creationDate;
		this.completionDate = completionDate;
		this.description = description;
	}
	
	
	
	

}
