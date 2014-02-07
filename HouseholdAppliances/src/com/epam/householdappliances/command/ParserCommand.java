package com.epam.householdappliances.command;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.householdappliances.exception.ControllerException;
import com.epam.householdappliances.parser.IProductsBuilder;
import com.epam.householdappliances.parser.dom.ProductsDOMBuilder;
import com.epam.householdappliances.parser.object.Category;
import com.epam.householdappliances.parser.sax.ProductsSAXBuilder;
import com.epam.householdappliances.parser.stax.ProductsStAXBuilder;
import com.epam.householdappliances.resource.ConfigurationManager;

public final class ParserCommand implements IActionCommand {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger.getLogger(ParserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {

		IProductsBuilder productBuiler = null;

		String parserType = request.getParameter("command");

		String productsXMLPath = request.getServletContext().getRealPath(
				ConfigurationManager.getProperty("path.xml.products"));

		Set<Category> categories = null;

		switch (parserType) {
		case "dom":
			productBuiler = ProductsDOMBuilder.getInstance();
			categories = productBuiler.buildCategoriesSet(productsXMLPath);
			break;
		case "sax":
			productBuiler = ProductsSAXBuilder.getInstance();
			categories = productBuiler.buildCategoriesSet(productsXMLPath);
			break;
		case "stax":
			productBuiler = ProductsStAXBuilder.getInstance();
			categories = productBuiler.buildCategoriesSet(productsXMLPath);
			break;
		default:
			LOG.error("parser type isn't recognized!");
			break;
		}

		request.setAttribute("categories", categories);

		String page = ConfigurationManager.getProperty("path.page.info");

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			LOG.error(e);
			throw new ControllerException(e);
		}
	}
}
