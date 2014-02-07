package com.epam.hibernate.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.epam.hibernate.database.IDAOFactory;
import com.epam.hibernate.database.IEmployeeDAO;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.presentation.form.EmployeeForm;
import com.epam.hibernate.presentation.form.PageItemInfo;
import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

public final class EmployeeAction extends DispatchAction {
	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(EmployeeAction.class);
	private static final String LIST_ITEM_ON_PAGE_KEY = PropertyManager
			.getProperty("tag.item.list", BundleName.CONFIGURATION);
	private final static String LIST_PAGE = "list";
	private final static int LIST_SIZE = 100;
	private final static int START_PAGE = 1;
	private IDAOFactory daoFactory;

	public void setDaoFactory(IDAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public ActionForward list(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IEmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
		EmployeeForm employeeForm = (EmployeeForm) actionForm;
		int countExistEmployee = employeeDAO.size();
		setValidPageData(employeeForm, countExistEmployee);
		PageItemInfo pageItemInfo = employeeForm.getPageItem();
		int itemOnPage = pageItemInfo.getItemOnPage();
		int currentPage = pageItemInfo.getCurrentPage();
		pageItemInfo.setListItemPage(LIST_ITEM_ON_PAGE_KEY);
		pageItemInfo.setItemCount(countExistEmployee);
		List<Employee> listEmployee = employeeDAO.getList((currentPage - 1)
				* itemOnPage, itemOnPage);
		employeeForm.setEmployeeList(listEmployee);
		return mapping.findForward(LIST_PAGE);
	}

	private void setValidPageData(EmployeeForm employeeForm,
			int countExistEmployee) {
		int itemOnPage = employeeForm.getPageItem().getItemOnPage();
		if (itemOnPage <= 0) {
			employeeForm.getPageItem().setItemOnPage(LIST_SIZE);
		}
		int currentPage = employeeForm.getPageItem().getCurrentPage();
		if (currentPage <= 0 || (currentPage - 1) * itemOnPage > countExistEmployee) {
			employeeForm.getPageItem().setCurrentPage(START_PAGE);
		}
	}
}
