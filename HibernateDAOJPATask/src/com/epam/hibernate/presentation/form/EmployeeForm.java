package com.epam.hibernate.presentation.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.epam.hibernate.model.Employee;

public final class EmployeeForm extends ActionForm {
	private static final long serialVersionUID = 3038512395106340206L;
	private List<Employee> employeeList;
	private PageItemInfo pageItem = new PageItemInfo();
	
	public EmployeeForm() {
	}
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public PageItemInfo getPageItem() {
		return pageItem;
	}

	public void setPageItem(PageItemInfo pageItem) {
		this.pageItem = pageItem;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}
}
