package com.genuinedeveloper.mysqlaccessserver.db_entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medications extends com.genuinedeveloper.mysqlaccessserver.db_entities.Entity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer medications_id;
	
	private Integer id;
	
	private String name;
	
	private String activeDrug;
	
	private Float amount;
	
	@Enumerated(EnumType.STRING)
	private UnitType amountUnit;
	
	private Integer frequencyAmount;
	
	@Enumerated(EnumType.STRING)
	private Frequency frequencyUnit;
	
	/*
	 * For patient changes to prescription or frequency, include a note
	 * in the description explaining the situation.
	 */
	private Date prescribedDate;
	
	private boolean activeStatus;
	
	private String description;
	
	public enum UnitType {
		g,
		mg,
		mcg,
		unit
	}
	
	public enum Frequency {
		minutes,
		hours,
		days,
		weeks,
		months,
		needed
	}

	@Column (name = "medications_id")
	public Integer getMedicationsId() {
		return medications_id;
	}

	public void setMedicationsId(Integer medicationsId) {
		this.medications_id = medicationsId;
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

	@Column (name = "active_drug")
	public String getActiveDrug() {
		return activeDrug;
	}

	public void setActiveDrug(String activeDrug) {
		this.activeDrug = activeDrug;
	}

	@Column (name = "amount")
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column (name = "amount_unit")
	public UnitType getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(UnitType amountUnit) {
		this.amountUnit = amountUnit;
	}

	@Column (name = "frequency_amount")
	public Integer getFrequencyAmount() {
		return frequencyAmount;
	}

	public void setFrequencyAmount(Integer frequencyAmount) {
		this.frequencyAmount = frequencyAmount;
	}

	@Column (name = "frequency_unit")
	public Frequency getFrequencyUnit() {
		return frequencyUnit;
	}

	public void setFrequencyUnit(Frequency frequencyUnit) {
		this.frequencyUnit = frequencyUnit;
	}

	@Column (name = "prescribed_date")
	public Date getPrescribedDate() {
		return prescribedDate;
	}

	public void setPrescribedDate(Date prescribedDate) {
		this.prescribedDate = prescribedDate;
	}

	@Column (name = "active_status")
	public boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Column (name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
