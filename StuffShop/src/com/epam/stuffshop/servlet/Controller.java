package com.epam.stuffshop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import com.epam.stuffshop.command.IActionCommand;
import com.epam.stuffshop.command.factory.ActionFactory;
import com.epam.stuffshop.exception.ControllerException;

/**
 * Controller: application controller. Process request data and forms response.
 * 
 * @author Yury Bakhmutski
 * @version 1.0
 * @since 2013-04-10
 */
@WebServlet(urlPatterns = { "/controller" })
public final class Controller extends HttpServlet {

	/** Defines uid class version */
	private static final long serialVersionUID = 1L;

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger.getLogger(Controller.class);

	/** keeps the real path to servlet */
	private static String webPath;

	public Controller() {
		super();
	}

	/**
	 * Initial logger and load database driver class.
	 */
	public void init() {
		LOG.info("Servlet is loaded!");
		webPath = getServletContext().getRealPath("/");

	}

	/**
	 * Handles requests redirecting get processing method madeRequest.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (ControllerException e) {
			throw new ServletException();
		} catch (TransformerException e) {
			LOG.error(e);
		}

	}

	/**
	 * Handles requests redirecting post processing method madeRequest.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (ControllerException e) {
			throw new ServletException();
		} catch (TransformerException e) {
			LOG.error(e);
		}
	}

	/**
	 * 
	 * @param request
	 *            is HttpServletRequest type object.
	 * @param response
	 *            is HttpServletResponse type object.
	 * @throws ServletException
	 * @throws IOException
	 * @throws ControllerException
	 *             if correct work can't be continue.
	 * @throws TransformerException
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ControllerException, TransformerException {

		ActionFactory client = new ActionFactory();
		IActionCommand command = client.defineCommand(request);
		command.execute(request, response);

	}

	public static String getWebPath() {
		return webPath;
	}

	/**
	 * Close connections in poll before destroying.
	 */
	public void destroy() {
		super.destroy();
	}
}
