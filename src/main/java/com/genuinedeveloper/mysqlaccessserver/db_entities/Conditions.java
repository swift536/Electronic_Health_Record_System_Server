package com.genuinedeveloper.mysqlaccessserver.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conditions extends com.genuinedeveloper.mysqlaccessserver.db_entities.Entity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer patients_id;
	
	private String name;
	
	private String notes;
	
	@Enumerated(EnumType.STRING)
	private Severity severity;
	
	public enum Severity {
		low, medium, high
	}

	@Column (name = "id")
	public Integer getConditions_id() {
		return id;
	}

	public void setConditions_id(Integer conditions_id) {
		this.id = conditions_id;
	}

	@Column (name = "patients_id")
	public Integer getId() {
		return patients_id;
	}

	public void setId(Integer id) {
		this.patients_id = id;
	}

	@Column (name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column (name = "notes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column (name = "severity")
	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	
}
