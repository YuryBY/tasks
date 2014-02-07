package com.epam.householdappliances.command.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.householdappliances.command.EmptyCommand;
import com.epam.householdappliances.command.IActionCommand;
import com.epam.householdappliances.command.util.CommandEnum;
import com.epam.householdappliances.exception.ControllerException;
import com.epam.householdappliances.resource.MessageManager;

/**
 * ActionFactory: create corresponding request object-command. And return
 * EmptyCommand if command isn't identified.
 * 
 * @author Yury Bakhmutski
 * @version 1.0
 * @since 2013-04-10
 */
public final class ActionFactory {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger.getLogger(ActionFactory.class);

	/**
	 * 
	 * @param HttpServletRequest
	 *            contains the necessary parameters and request attributes.
	 * @return corresponding request object-command or EmptyCommand if command
	 *         isn't identified.
	 * @throws ControllerException
	 * @throws ControllerException
	 *             called if command wasn't identified.
	 */
	public IActionCommand defineCommand(HttpServletRequest request)
			throws ControllerException {
		IActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");

		if (action == null || action.isEmpty()) {
			return current;
		}

		action = action.replace(" ", "_").toUpperCase();

		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action);
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			LOG.error(MessageManager.getProperty("message.wrongaction")
					+ action);
			throw new ControllerException(
					MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}
