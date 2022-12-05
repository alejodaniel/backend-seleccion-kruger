package com.kruger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.kruger.enums.VaccinationStatusEnum;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmployee;

	@Column(name = "identification", unique = true, length = 10)
	private Integer identification;

	@Column(name = "names", length = 120)
	private String names;

	@Column(name = "last_names", length = 120)
	private String lastNames;

	@Column(name = "email", length = 100)
	private String email;

	@JsonSerialize(using = ToStringSerializer.class)
	private Date birthDate;

	@Column(name = "address")
	private String address;

	@Column(name = "cell_phone")
	private Integer cellPhone;

	@Column(name = "vaccination_status")
	private VaccinationStatusEnum vaccinationstatus;

	// @JsonSerialize(using = ToStringSerializer.class)
	// private LocalDate vaccineDate;

	private String vaccineDate;

	@Column(name = "dose_number")
	private Integer doseNumber;

	@JsonSerialize(using = ToStringSerializer.class)
	private Timestamp createdAt;

	@JsonSerialize(using = ToStringSerializer.class)
	private Timestamp updatedAt;

	private String createdBy;

	private String updatedBy;

	private Integer isDeleted;

	@Column(name = "vaccine_type_id")
	private Integer vaccineTypeId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vaccine_type_id", insertable = false, updatable = false)
	private Catalog vaccineType;

	@Column(name = "id_user")
	private int idUser;

	@OneToOne
	@JoinColumn(name = "id_user", insertable = false, updatable = false)
	private User user;

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Integer getIdentification() {
		return identification;
	}

	public void setIdentification(Integer identification) {
		this.identification = identification;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(Integer cellPhone) {
		this.cellPhone = cellPhone;
	}

	public VaccinationStatusEnum getVaccinationstatus() {
		return vaccinationstatus;
	}

	public void setVaccinationstatus(VaccinationStatusEnum vaccinationstatus) {
		this.vaccinationstatus = vaccinationstatus;
	}

	public String getVaccineDate() {
		return vaccineDate;
	}

	public void setVaccineDate(String vaccineDate) {
		this.vaccineDate = vaccineDate;
	}

	public Integer getDoseNumber() {
		return doseNumber;
	}

	public void setDoseNumber(Integer doseNumber) {
		this.doseNumber = doseNumber;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getVaccineTypeId() {
		return vaccineTypeId;
	}

	public void setVaccineTypeId(Integer vaccineTypeId) {
		this.vaccineTypeId = vaccineTypeId;
	}

	public Catalog getVaccineType() {
		return vaccineType;
	}

	public void setVaccineType(Catalog vaccineType) {
		this.vaccineType = vaccineType;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
