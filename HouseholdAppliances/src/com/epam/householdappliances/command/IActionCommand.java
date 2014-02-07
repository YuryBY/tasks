package com.epam.householdappliances.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.householdappliances.exception.ControllerException;

/**
 * IActionCommand: interface which is implemented by all existing commands.
 * 
 * @author Yury Bakhmutski
 * @version 1.0
 * @since 2013-04-10
 */

public interface IActionCommand {

	/**
	 * The contents of the object sessionRequestContent returns the page to go.
	 * 
	 * @param HttpServletRequest
	 *            contains the necessary parameters and request attributes.
	 * @return reference to answer-page for controller module.
	 * @throws ControllerException
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException;
}
