package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Employee;
import com.demo.entities.VacationRequest;
import com.demo.entities.VacationType;
import com.demo.repositories.EmployeeRepository;
import com.demo.repositories.VacationRequestRepository;
import com.demo.repositories.VacationTypeRepository;

@Service
public class VacationService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	VacationTypeRepository vacationTypeRepository;

	@Autowired
	VacationRequestRepository vacationRequestRepository;

	public List<VacationType> getAllVacationTypes() {
		return vacationTypeRepository.findAll();
	}

	public Employee getEmployeeVacationsBalanceByEmpId(long empId) {
		return employeeRepository.findOne(empId);
	}

	@Transactional
	public void addEmployeeVacationRequest(VacationRequest vacationRequest, Employee employee) {
		vacationRequestRepository.save(vacationRequest);
		employeeRepository.save(employee);

	}
}
