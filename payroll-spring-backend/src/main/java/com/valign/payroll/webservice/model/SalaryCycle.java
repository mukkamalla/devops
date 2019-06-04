package com.valign.payroll.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scyl")
public class SalaryCycle {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String salaryCycleName;
	@Column
	private java.sql.Date cycleStartDate;
	@Column
	private java.sql.Date cycleEndDate;
	@Column
	private Integer attendancePushed;   // 0 is yet to push from Bio metric device, 1 means pushed from Bio Metric device
	// the attendance can now be appropriated.
	@Column
	private Integer attendanceProcessed; // 0 is yet to process attendance, 1 means attendance process and salary componenets generated for all employees
	@Column
	private Integer cycleStatus; //FK with table scsv // SalaryCycleStatusValues
	@Column
	private Integer recordStatus; // FK with record_statues table
	@Column
	private java.util.Date creationDate;
	@Column
	private Integer creationBy; // FK with payroll_users table
	@Column
	private java.util.Date updatedDate;
	@Column
	private Integer updatedBy; // FK with payroll_users table 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSalaryCycleName() {
		return salaryCycleName;
	}
	public void setSalaryCycleName(String salaryCycleName) {
		this.salaryCycleName = salaryCycleName;
	}
	public java.sql.Date getCycleStartDate() {
		return cycleStartDate;
	}
	public void setCycleStartDate(java.sql.Date cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}
	public java.sql.Date getCycleEndDate() {
		return cycleEndDate;
	}
	public void setCycleEndDate(java.sql.Date cycleEndDate) {
		this.cycleEndDate = cycleEndDate;
	}
	public Integer getAttendancePushed() {
		return attendancePushed;
	}
	public void setAttendancePushed(Integer attendancePushed) {
		this.attendancePushed = attendancePushed;
	}
	public Integer getAttendanceProcessed() {
		return attendanceProcessed;
	}
	public void setAttendanceProcessed(Integer attendanceProcessed) {
		this.attendanceProcessed = attendanceProcessed;
	}
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	public java.util.Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreationBy() {
		return creationBy;
	}
	public void setCreationBy(Integer creationBy) {
		this.creationBy = creationBy;
	}
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getCycleStatus() {
		return cycleStatus;
	}
	public void setCycleStatus(Integer cycleStatus) {
		this.cycleStatus = cycleStatus;
	}
	
}