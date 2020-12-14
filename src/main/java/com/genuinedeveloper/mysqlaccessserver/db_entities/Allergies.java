package com.genuinedeveloper.mysqlaccessserver.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Allergies extends com.genuinedeveloper.mysqlaccessserver.db_entities.Entity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer patients_id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private ThreatLevel threat_level;
	
	private String description;
	
	public enum ThreatLevel {
		low,
		medium,
		high
	}

	@Column (name = "id")
	public Integer getAllergysId() {
		return id;
	}
	
	public void setAllergysId(Integer allergiesId) {
		this.id = allergiesId;
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

	@Column (name = "threat_level")
	public ThreatLevel getThreatLevel() {
		return threat_level;
	}

	public void setThreatLevel(ThreatLevel threat_level) {
		this.threat_level = threat_level;
	}

	@Column (name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
