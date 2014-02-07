package com.epam.householdappliances.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.householdappliances.exception.ControllerException;
import com.epam.householdappliances.resource.ConfigurationManager;
import com.epam.householdappliances.resource.MessageManager;

/**
 * 
 * @author Yury Bakhmutski
 * @version 1.0
 * @since 2013-04-10
 */
public final class EmptyCommand implements IActionCommand {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private Logger LOG = Logger.getLogger(EmptyCommand.class);

	/**
	 * Set locale and return path to main page
	 * 
	 * @param sessionRequestContent
	 *            contains the necessary parameters and request attributes.
	 * @return reference to answer-page.
	 * @throws ControllerException
	 *             if path to page was not found
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {
		String page = ConfigurationManager.getProperty("path.page.index");
		String locale = (String) request.getSession().getAttribute("locale");
		if (page == null) {
			LOG.error(MessageManager.getProperty("command.pathNotFound"));
			throw new ControllerException(
					MessageManager.getProperty("command.pathNotFound"));
		}
		if (locale == null || locale.isEmpty()) {
			request.getSession().setAttribute("locale", "EN");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			LOG.error(e);
			throw new ControllerException(e);
		}
	}

}
