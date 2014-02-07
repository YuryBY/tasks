package com.epam.employees.presentation.form;

import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.epam.employees.model.Employee;

public final class EmployeeForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	private List<Employee> employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
