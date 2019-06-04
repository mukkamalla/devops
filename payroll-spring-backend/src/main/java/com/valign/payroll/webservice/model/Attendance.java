package com.valign.payroll.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atdnc")
public class Attendance {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private java.util.Date attendanceDate;
	@Column
	private java.sql.Time checkInTime;
	@Column
	private java.sql.Time checkOutTime;
	@Column
	private Integer attendanceSource1;   // FK with attendance Source
	@Column
	private Integer employeeId; // FK with emp table
	@Column
	private Integer attendanceStatus1;
	
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
	public java.util.Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(java.util.Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public java.sql.Time getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(java.sql.Time checkInTime) {
		this.checkInTime = checkInTime;
	}
	public java.sql.Time getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(java.sql.Time checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getAttendanceSource1() {
		return attendanceSource1;
	}
	public void setAttendanceSource1(Integer attendanceSource1) {
		this.attendanceSource1 = attendanceSource1;
	}
	public Integer getAttendanceStatus1() {
		return attendanceStatus1;
	}
	public void setAttendanceStatus1(Integer attendanceStatus1) {
		this.attendanceStatus1 = attendanceStatus1;
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
	
	
}