package com.jobtrack.dto;

public class ApplicationRequestDto {

	
	private Long jobId;
	private String notes;
	
	public ApplicationRequestDto() {
		
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
