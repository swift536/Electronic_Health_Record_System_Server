package com.genuinedeveloper.mysqlaccessserver.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Surgeries extends com.genuinedeveloper.mysqlaccessserver.db_entities.Entity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer patients_id;
	
	private String name;
	
	private String notes;

	@Column (name = "id")
	public Integer getSurgeries_id() {
		return id;
	}

	public void setSurgeries_id(Integer surgeries_id) {
		this.id = surgeries_id;
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
	
	
}
