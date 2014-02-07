package com.epam.stuffshop.resource;

import org.apache.log4j.Logger;

public enum ParametersEnum {
	CATEGORY_NAME_JSP("categoryName"), 
	SUBCATEGORY_NAME_JSP("subcategoryName"),
	PRODUCT_NAME_JSP("name"),
	PRODUCT_PRODUCER_JSP("producer"),
	PRODUCT_MODEL_JSP("model"),
	PRODUCT_DATE_ISSUE_JSP("date"),
	PRODUCT_COLOR_JSP("color"),
	PRODUCT_PRICE_JSP("price"),
	
	CATEGORY_NAME_TRANSFORMARION("categoryName"), 
	SUBCATEGORY_NAME_TRANSFORMARION("subcategoryName"),
	PRODUCT_NAME_TRANSFORMARION("productName"),
	PRODUCT_PRODUCER_TRANSFORMARION("producer"),
	PRODUCT_MODEL_TRANSFORMARION("model"),
	PRODUCT_DATE_ISSUE_TRANSFORMARION("date"),
	PRODUCT_COLOR_TRANSFORMARION("color"),
	PRODUCT_PRICE_TRANSFORMARION("price"),
	PRODUCT_IS_MISS_TRANSFORMARION("miss"),
	VALIDATION_UTIL_OBJECT("validationUtil"),
	WRONG_PRODUCT_NAME("wrongProductName"),
	WRONG_PRODUCER("wrongProducer"),
	WRONG_MODEL("wrongModel"),
	WRONG_DATE("wrongDateIssue"),
	WRONG_COLOR("wrongColor"),
	WRONG_PRICE("wrongPrice");
	
	
	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ParametersEnum.class);

	/** contain corresponding of name object-command */
	private String parameterName;

	private ParametersEnum(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterName() {
		return parameterName;
	}
	
}
