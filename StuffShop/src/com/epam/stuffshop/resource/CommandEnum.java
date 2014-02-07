package com.epam.stuffshop.resource;

import org.apache.log4j.Logger;

import com.epam.stuffshop.command.IActionCommand;
import com.epam.stuffshop.command.ToMainPageCommand;
import com.epam.stuffshop.command.catalog.AddProduct;
import com.epam.stuffshop.command.catalog.FormCancel;
import com.epam.stuffshop.command.catalog.ShowAddForm;
import com.epam.stuffshop.command.catalog.ShowCategories;
import com.epam.stuffshop.command.catalog.ShowProducts;
import com.epam.stuffshop.command.catalog.ShowSubcategories;

/**
 * CommandEnum: defines all commands in application.
 * 
 * @author Yury Bakhmutski
 * @version 1.1
 * @since 2013-04-10
 */
public enum CommandEnum {
	TO_MAIN, TO_CATEGORIES, TO_SUBCATEGORIES, TO_PRODUCTS, TO_ADD_FORM, SAVE_PRODUCT, CANCEL;

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
		case "TO_CATEGORIES":
			this.command = new ShowCategories();
			break;
		case "TO_SUBCATEGORIES":
			this.command = new ShowSubcategories();
			break;
		case "TO_PRODUCTS":
			this.command = new ShowProducts();
			break;
		case "TO_ADD_FORM":
			this.command = new ShowAddForm();
			break;
		case "SAVE_PRODUCT":
			this.command = new AddProduct();
			break;
		case "CANCEL":
			this.command = new FormCancel();
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
