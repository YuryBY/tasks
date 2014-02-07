package com.epam.stuffshop.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.stuffshop.exception.ControllerException;
import com.epam.stuffshop.resource.ConfigurationManager;
import com.epam.stuffshop.resource.MessageManager;

public final class ToMainPageCommand implements IActionCommand {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger.getLogger(ToMainPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {

		String page = ConfigurationManager.getProperty("path.page.main");

		if (page == null) {
			LOG.error(MessageManager.getProperty("command.pathNotFound"));
			throw new ControllerException(
					MessageManager.getProperty("command.pathNotFound"));
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
