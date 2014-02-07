package com.epam.task9.presentation;

import com.epam.task9.resource.NewsFeedConstants;

public class LocaleBean {

	String locale;

	public LocaleBean() {
		locale = NewsFeedConstants.APPLICATION_DEFAULT_LOCALE;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
