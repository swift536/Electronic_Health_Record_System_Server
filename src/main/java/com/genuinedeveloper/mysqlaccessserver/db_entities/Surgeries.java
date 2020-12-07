package com.genuinedeveloper.mysqlaccessserver.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Surgeries {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer surgeries_id;
	
	private Integer id;
	
	private String name;
	
	private String notes;

	@Column (name = "surgeries_id")
	public Integer getSurgeries_id() {
		return surgeries_id;
	}

	public void setSurgeries_id(Integer surgeries_id) {
		this.surgeries_id = surgeries_id;
	}

	@Column (name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
