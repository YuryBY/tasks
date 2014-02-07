package com.epam.employees.database.dao.hibernate;

import com.epam.employees.model.Employee;

public final class EmployeeDAO extends AbstractDAO<Employee> {

	public EmployeeDAO() {
		super(Employee.class);
	}

}
