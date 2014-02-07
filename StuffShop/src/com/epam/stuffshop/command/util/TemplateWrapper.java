package com.epam.stuffshop.command.util;

import java.util.Calendar;

import javax.xml.transform.Templates;

public final class TemplateWrapper {

	private Calendar lastModifiedDate;
	private Templates templates;

	public TemplateWrapper() {
		super();
	}

	public TemplateWrapper(Calendar lastModifiedDate, Templates templates) {
		super();
		this.lastModifiedDate = lastModifiedDate;
		this.templates = templates;
	}

	public Calendar getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Calendar lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Templates getTemplates() {
		return templates;
	}

	public void setTemplates(Templates templates) {
		this.templates = templates;
	}

}
