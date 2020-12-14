package com.genuinedeveloper.mysqlaccessserver.db_entities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genuinedeveloper.mysqlaccessserver.MainController;
import com.genuinedeveloper.mysqlaccessserver.db_entities.db_sub_entitities.SOAPNote;

@Entity
public class Records extends com.genuinedeveloper.mysqlaccessserver.db_entities.Entity{
	
	@Transient
	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//@Autowired
	@Transient
	private ObjectMapper objectMapper;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer patients_id;
	
	private Date date_date;
	
	private Time date_time;
	
	private String soap_note;
	
	@Column (name = "id")
	public Integer getRecordId() {
		return id;
	}
	
	public void setRecordId(Integer recordId) {
		this.id = recordId;
	}
	
	@Column (name = "patients_id")
	public Integer getId() {
		return patients_id;
	}

	public void setId(Integer id) {
		this.patients_id = id;
	}

	@Column (name = "date_date")
	public Date getDate() {
		return date_date;
	}

	public void setDate(Date date_date) {
		this.date_date = date_date;
	}

	@Column (name = "date_time")
	public Time getDateTime() {
		return date_time;
	}

	public void setDateTime(Time date_time) {
		this.date_time = date_time;
	}
	
	@Column (name = "soap_note")
	public SOAPNote getSoapNote() throws JsonProcessingException {
		
		objectMapper = new ObjectMapper();
		
		logger.info(soap_note);
		
		return objectMapper.readValue(soap_note, SOAPNote.class);
	
	}

	public void setSoapNote(SOAPNote soapNote) throws JsonMappingException, JsonProcessingException {
		
		objectMapper = new ObjectMapper();
		
		soap_note = objectMapper.writeValueAsString(soapNote);
		
	}
	
}
