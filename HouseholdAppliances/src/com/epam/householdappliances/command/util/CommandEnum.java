package com.epam.householdappliances.command.util;

import org.apache.log4j.Logger;

import com.epam.householdappliances.command.IActionCommand;
import com.epam.householdappliances.command.ParserCommand;
import com.epam.householdappliances.command.ToMainPageCommand;

/**
 * CommandEnum: defines all commands in application.
 * 
 * @author Yury Bakhmutski
 * @version 1.1
 * @since 2013-04-10
 */
public enum CommandEnum {
	DOM, SAX, STAX, TO_MAIN, TO_CATALOG, ADD_GOODS, SAVE_GOODS, CANCEL;

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(CommandEnum.class);

	/** contain corresponding of name object-command */
	IActionCommand command;

	private CommandEnum() {
		switch (this.name()) {
		case "TO_MAIN":
			this.command = new ToMainPageCommand();
			break;
		case "DOM":
		case "SAX":
		case "STAX":
			this.command = new ParserCommand();
			break;
		default:
			System.out.println("ENUM command isn't defined!");
			break;
		}
	}

	/**
	 * @return object implements interface IActionCommand which contain link
	 *         command.
	 */
	public IActionCommand getCurrentCommand() {
		return command;
	}
}
