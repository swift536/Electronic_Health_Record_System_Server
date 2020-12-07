package com.genuinedeveloper.mysqlaccessserver.db_entities.db_sub_entitities;

import java.util.Vector;

public class Subjective {
	
	private Symptom[] symptoms;
	private String notes;
	
	public Symptom[] getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(Symptom[] symptoms) {
		this.symptoms = symptoms;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
