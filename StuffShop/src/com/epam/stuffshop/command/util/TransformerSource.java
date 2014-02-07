package com.epam.stuffshop.command.util;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

public final class TransformerSource {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(TransformerSource.class);

	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private static final Lock readLock = lock.readLock();
	private static final Lock writeLock = lock.writeLock();

	private static final HashMap<String, TemplateWrapper> TEMPLATES = new HashMap<>();

	private TransformerSource() {
	}

	public static Transformer newTransformer(String xsltFileName)
			throws TransformerConfigurationException {
		Templates templates = getTemplates(xsltFileName);
		Transformer transformer = templates.newTransformer();
		return transformer;
	}

	private static Templates getTemplates(String xsltFileName)
			throws TransformerConfigurationException {
		TemplateWrapper templateWrapper;
		Templates templates = null;
		File xsltFile = new File(xsltFileName);
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(xsltFile.lastModified());

		readLock.lock();
		try {
			templateWrapper = TEMPLATES.get(xsltFileName);
		} finally {
			readLock.unlock();
		}
		if (templateWrapper != null) {

			boolean isEqualedCashObject;
			readLock.lock();
			try {
				isEqualedCashObject = date.equals(templateWrapper
						.getLastModifiedDate());
			} finally {
				readLock.unlock();
			}

			if (!isEqualedCashObject) {

				Source xslSource = new StreamSource(xsltFileName);
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				templates = transformerFactory.newTemplates(xslSource);
				templateWrapper = new TemplateWrapper(date, templates);

				writeLock.lock();
				try {
					TEMPLATES.put(xsltFileName, templateWrapper);
				} finally {
					writeLock.unlock();
				}
			}
		} else {
			Source xslSource = new StreamSource(xsltFileName);
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			templates = transformerFactory.newTemplates(xslSource);
			templateWrapper = new TemplateWrapper(date, templates);

			writeLock.lock();
			try {
				TEMPLATES.put(xsltFileName, templateWrapper);
			} finally {
				writeLock.unlock();
			}
		}

		return templateWrapper.getTemplates();
	}
}
