package com.valign.payroll.webservice.model;

import javax.persistence.*;

@Entity
@Table(name = "emp")
public class Employee {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String employeeCode;
	@Column
	private Integer salutation; // FK with salutations table
	@Column
	private String firstName;
	@Column
	private String middleName;
	@Column
	private String lastName;
	@Column
	private String emailId;
	@Column
	private String phoneNumber;
	@Column
	private String mobileNumber;
	@Column
	private Integer gender; // FK with gender table
	@Column
	private Integer empStatus; // FK with empst table
	@Column
	private java.util.Date dateOfBirth;
	@Column
	private java.util.Date dateOfJoining;

	@Column
	private String panNumber;
	@Column
	private String aadharNumber;
	@Column
	private String tempAddress1;
	@Column
	private String tempAddress2;
	@Column
	private Integer tempCountryId;
	@Column
	private String tempState;
	@Column
	private String tempCity;
	@Column
	private String tempPostalCode;

	@Column
	private String permAddress1;
	@Column
	private String permAddress2;
	@Column
	private Integer permCountryId;
	@Column
	private String permState;
	@Column
	private String permCity;
	@Column
	private String permPostalCode;
	@Column
	private Integer departmentId; // FK with departments table
	@Column
	private Integer locationId; // FK with locations table
	@Column
	private Integer designationId; // FK with designations table
	@Column
	private Integer gradeId; // FK with grades table
	@Column
	private Integer roleId; // FK with role table
	@Column
	private String bankAccountNumber;
	@Column
	private String bankIFSCCode;

	@Column
	private Integer companyId; // FK with companies table
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

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Integer getSalutation() {
		return salutation;
	}

	public void setSalutation(Integer salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public java.util.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.util.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public java.util.Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(java.util.Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getTempAddress1() {
		return tempAddress1;
	}

	public void setTempAddress1(String tempAddress1) {
		this.tempAddress1 = tempAddress1;
	}

	public String getTempAddress2() {
		return tempAddress2;
	}

	public void setTempAddress2(String tempAddress2) {
		this.tempAddress2 = tempAddress2;
	}

	public Integer getTempCountryId() {
		return tempCountryId;
	}

	public void setTempCountryId(Integer tempCountryId) {
		this.tempCountryId = tempCountryId;
	}

	public String getTempState() {
		return tempState;
	}

	public void setTempState(String tempState) {
		this.tempState = tempState;
	}

	public String getTempCity() {
		return tempCity;
	}

	public void setTempCity(String tempCity) {
		this.tempCity = tempCity;
	}

	public String getTempPostalCode() {
		return tempPostalCode;
	}

	public void setTempPostalCode(String tempPostalCode) {
		this.tempPostalCode = tempPostalCode;
	}

	public String getPermAddress1() {
		return permAddress1;
	}

	public void setPermAddress1(String permAddress1) {
		this.permAddress1 = permAddress1;
	}

	public String getPermAddress2() {
		return permAddress2;
	}

	public void setPermAddress2(String permAddress2) {
		this.permAddress2 = permAddress2;
	}

	public Integer getPermCountryId() {
		return permCountryId;
	}

	public void setPermCountryId(Integer permCountryId) {
		this.permCountryId = permCountryId;
	}

	public String getPermState() {
		return permState;
	}

	public void setPermState(String permState) {
		this.permState = permState;
	}

	public String getPermCity() {
		return permCity;
	}

	public void setPermCity(String permCity) {
		this.permCity = permCity;
	}

	public String getPermPostalCode() {
		return permPostalCode;
	}

	public void setPermPostalCode(String permPostalCode) {
		this.permPostalCode = permPostalCode;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankIFSCCode() {
		return bankIFSCCode;
	}

	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}

}