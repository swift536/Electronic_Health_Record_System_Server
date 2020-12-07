package com.genuinedeveloper.mysqlaccessserver.db_entities.db_sub_entitities;

public class Symptom {
	
	private String complaint;
	
	private Integer severity;
	
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	
}
