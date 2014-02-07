package com.epam.hibernate.tag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

public final class PageItemTag extends TagSupport {
	private static final long serialVersionUID = 1448401928397850803L;
	private static final Logger LOGGER = Logger.getLogger(PageItemTag.class);
	private static final String ERROR_VALUE_MESSAGE_KEY = "tag.value.countOnPage";
	private String valueList;
	private int currentNumber;

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}

	public void setValueList(String valueList) {
		this.valueList = valueList;
	}

	@Override
	public int doStartTag() throws JspTagException {
		JspWriter out = pageContext.getOut();
		try {
			out.write("<div class='itemPages'>Count Notes On Page: <i>");
			String[] valuesArray = valueList.split(",");
			for (String currentValue : valuesArray) {
				int numericValue = validate(currentValue);
				out.write("<a ");
				if (numericValue == currentNumber) {
					out.write("class='currentNumber' ");
				}
				out.write("href='EmployeeAction.do?method=list&pageItem.itemOnPage="
						+ numericValue + "'>");
				out.write(currentValue.trim());
				out.write("</a> ");
			}
			out.write("</i></div>");
		} catch (IOException e) {
			LOGGER.error(e);
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

	private int validate(String currentValue) throws JspTagException {
		currentValue = currentValue.trim();
		if (!isInteger(currentValue)) {
			throw new JspTagException(PropertyManager.getProperty(
					ERROR_VALUE_MESSAGE_KEY, BundleName.ERROR_MESSAGES));
		}
		return Integer.parseInt(currentValue);
	}
	
	private boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
