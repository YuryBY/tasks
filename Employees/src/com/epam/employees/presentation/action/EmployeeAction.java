package com.epam.employees.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.epam.employees.database.dao.IDAO;
import com.epam.employees.model.Employee;
import com.epam.employees.presentation.form.EmployeeForm;
import com.epam.employees.resource.DBQueryConstants;
import com.epam.employees.resource.ForwardNamesConstants;
import com.epam.employees.util.db.DBCreator;

public final class EmployeeAction extends DispatchAction {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(EmployeeAction.class);

	private IDAO<Employee> employeeDAO;

	public ActionForward fill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		new DBCreator().fillDataBase();
		EmployeeForm employeeForm = (EmployeeForm) form;

		List<Employee> employeeList = employeeDAO.getDefinedQuantity(
				DBQueryConstants.FIRST_RESULT, DBQueryConstants.MAX_RESULT);

		employeeForm.setEmployeeList(employeeList);
		return mapping.findForward(ForwardNamesConstants.EMPLOYEE_LIST);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmployeeForm employeeForm = (EmployeeForm) form;

		List<Employee> employeeList = employeeDAO.getDefinedQuantity(
				DBQueryConstants.FIRST_RESULT, DBQueryConstants.MAX_RESULT);

		employeeForm.setEmployeeList(employeeList);

		return mapping.findForward(ForwardNamesConstants.EMPLOYEE_LIST);
	}

	public void setEmployeeDAO(IDAO<Employee> employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
}
