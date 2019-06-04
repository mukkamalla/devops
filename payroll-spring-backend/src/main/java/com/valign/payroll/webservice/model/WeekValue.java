package com.valign.payroll.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wval")
public class WeekValue {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String weekValueName; // values are First Week, Second Week, Third Week
	@Column
	private int weekValueValue; // value are 1, 2, 3, 4, 5 //meaning 1st week , 2nd week... so on
	@Column
	private int localeId; // FK with locale table primary key
	@Column
	private int recordStatus; // FK with record_statues table
	@Column
	private java.util.Date creationDate;
	@Column
	private int creationBy; // FK with payroll_users table
	@Column
	private java.util.Date updatedDate;
	@Column
	private int updatedBy; // FK with payroll_users table

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeekValueName() {
		return weekValueName;
	}

	public void setWeekValueName(String weekValueName) {
		this.weekValueName = weekValueName;
	}

	public int getWeekValueValue() {
		return weekValueValue;
	}

	public void setWeekValueValue(int weekValueValue) {
		this.weekValueValue = weekValueValue;
	}

	

	public int getLocaleId() {
		return localeId;
	}

	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public java.util.Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getCreationBy() {
		return creationBy;
	}

	public void setCreationBy(int creationBy) {
		this.creationBy = creationBy;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

}