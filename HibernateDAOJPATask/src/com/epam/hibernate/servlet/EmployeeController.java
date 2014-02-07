package com.epam.hibernate.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts.action.ActionServlet;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public final class EmployeeController extends ActionServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGGER_PATH = "log4j_init_file";
	private static final String START_WORL_MESSAGE = "Controller start work.";
	private static Logger logger;
	private static String servletPath;

	/**
	 * @see ActionServlet#ActionServlet()
	 */
	public EmployeeController() {
		super();
	}

	public static String getServletPath() {
		return servletPath;
	}

	@Override
	public void init() throws ServletException {
		servletPath = getServletContext().getRealPath("/");
		String filename = getInitParameter(LOGGER_PATH);
		if (filename != null) {
			new DOMConfigurator().doConfigure(servletPath + filename,
					LogManager.getLoggerRepository());
		}
		logger = Logger.getLogger(EmployeeController.class);
		logger.info(START_WORL_MESSAGE);
		super.init();
	}
}
