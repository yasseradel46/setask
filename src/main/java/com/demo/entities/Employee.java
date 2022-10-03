package com.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;

	@Column(name = "ANNUAL_BALANCE")
	private Integer annualBalance;

	@Column(name = "SICK_BALANCE")
	private Integer sickBalance;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VacationRequest> vacationRequests;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getAnnualBalance() {
		return annualBalance;
	}

	public void setAnnualBalance(Integer annualBalance) {
		this.annualBalance = annualBalance;
	}

	public Integer getSickBalance() {
		return sickBalance;
	}

	public void setSickBalance(Integer sickBalance) {
		this.sickBalance = sickBalance;
	}

	public List<VacationRequest> getVacationRequests() {
		return vacationRequests;
	}

	public void setVacationRequests(List<VacationRequest> vacationRequests) {
		this.vacationRequests = vacationRequests;
	}

}
