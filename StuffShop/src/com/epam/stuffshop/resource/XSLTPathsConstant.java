package com.epam.stuffshop.resource;

import com.epam.stuffshop.servlet.Controller;

public final class XSLTPathsConstant {
	// XML File
	public static final String XML_FILE_PATH = Controller.getWebPath()
			+ "data\\xml\\products.xml";

	// XSL Files
	public static final String CATEGORIES_FILE_PATH = Controller.getWebPath()
			+ "data\\xsl\\categories.xsl";
	public static final String SUBCATEGORIES_FILE_PATH = Controller
			.getWebPath() + "data\\xsl\\subcategories.xsl";
	public static final String PRODUCTS_FILE_PATH = Controller.getWebPath()
			+ "data\\xsl\\products.xsl";
	public static final String PRODUCT_FORM_FILE_PATH = Controller.getWebPath()
			+ "data\\xsl\\form.xsl";
	public static final String ADD_PRODUCT_FILE_PATH = Controller.getWebPath()
			+ "data\\xsl\\addProduct.xsl";
	public static final String VALIDATE_PRODUCT_FILE_PATH = Controller
			.getWebPath() + "data\\xsl\\validate.xsl";

	private XSLTPathsConstant() {

	}
}
