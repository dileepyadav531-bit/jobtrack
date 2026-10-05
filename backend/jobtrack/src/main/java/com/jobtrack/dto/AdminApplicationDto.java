package com.jobtrack.dto;

public class AdminApplicationDto {
private Long applicationId;
private Long userId;
private String userName;
private String userEmail;
private Long jobId;
private String jobTitle;
private String company;
private String status;
private String appliedDate;

public AdminApplicationDto() {
	
}

public Long getApplicationId() {
	return applicationId;
}

public void setApplicationId(Long applicationId) {
	this.applicationId = applicationId;
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserEmail() {
	return userEmail;
}

public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}

public Long getJobId() {
	return jobId;
}

public void setJobId(Long jobId) {
	this.jobId = jobId;
}

public String getJobTitle() {
	return jobTitle;
}

public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
}

public String getCompany() {
	return company;
}

public void setCompany(String company) {
	this.company = company;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getAppliedDate() {
	return appliedDate;
}

public void setAppliedDate(String appliedDate) {
	this.appliedDate = appliedDate;
}




}
