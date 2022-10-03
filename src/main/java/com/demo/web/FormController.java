package com.demo.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.demo.entities.Employee;
import com.demo.entities.VacationRequest;
import com.demo.entities.VacationType;
import com.demo.services.VacationService;

@Component("formController")
@Scope("view")
public class FormController {

	@Autowired
	VacationService vacationService;

	private Employee employee;

	private List<VacationType> vacationTypes;

	private VacationRequest request;

	@PostConstruct
	public void init() {
		vacationTypes = vacationService.getAllVacationTypes();
		employee = vacationService.getEmployeeVacationsBalanceByEmpId(1);
		request = new VacationRequest();
		VacationType vacationType = new VacationType();
		request.setVacationType(vacationType);
		request.setEmployee(employee);
		request.setStartDate(new Date());
		request.setEndDate(new Date());
		request.setVacationDays(getVacationDaysExcludingWeekend(request.getStartDate(), request.getEndDate()));
	}

	// Saving new vacation Request After passing Validation
	public void addVacationRequest() {
		if (validateVactionRequest()) {
			vacationService.addEmployeeVacationRequest(request, employee);
			init();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Request Created", "");
			FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
		} else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date or Balance Validation Error",
					"");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
	}

	// view Employee Vacation Balance
	public void getEmployeeVacationBalance() {
		employee = vacationService.getEmployeeVacationsBalanceByEmpId(1);
	}

	// Validate Balance Request
	private boolean validateVactionRequest() {
		int days = request.getVacationDays();
		if (request.getVacationDays() > 0) {
			if (request.getVacationType() != null && request.getVacationType().getId() == 1) {
				if (employee.getAnnualBalance() >= days) {
					employee.setAnnualBalance(employee.getAnnualBalance() - days);
					return true;
				} else
					return false;
			} else {
				if (employee.getSickBalance() >= days) {
					employee.setSickBalance(employee.getSickBalance() - days);
					return true;
				} else
					return false;
			}
		}
		return false;
	}

	// Calculating Vacation Days Excluding Weekend
	private int getVacationDaysExcludingWeekend(Date start, Date end) {
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(start);
		endCal.setTime(end);

		int numberOfDays = 0;
		while (startCal.before(endCal) || startCal.equals(endCal)) {
			if ((Calendar.SATURDAY != startCal.get(Calendar.DAY_OF_WEEK))
					&& (Calendar.FRIDAY != startCal.get(Calendar.DAY_OF_WEEK))) {
				numberOfDays++;
			}
			startCal.add(Calendar.DATE, 1);
		}
		return numberOfDays;
	}
	
	// Calculating the Vacations Days on Start Date Calendar Change
	public void onStartDateSelect(SelectEvent event) {
		Date start = (Date) event.getObject();
		request.setVacationDays(getVacationDaysExcludingWeekend(start, request.getEndDate()));
	}

	// Calculating the Vacations Days on End Date Calendar Change
	public void onEndDateSelect(SelectEvent event) {
		Date end = (Date) event.getObject();
		request.setVacationDays(getVacationDaysExcludingWeekend(request.getStartDate(), end));
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<VacationType> getVacationTypes() {
		return vacationTypes;
	}

	public void setVacationTypes(List<VacationType> vacationTypes) {
		this.vacationTypes = vacationTypes;
	}

	public VacationRequest getRequest() {
		return request;
	}

	public void setRequest(VacationRequest request) {
		this.request = request;
	}

}
