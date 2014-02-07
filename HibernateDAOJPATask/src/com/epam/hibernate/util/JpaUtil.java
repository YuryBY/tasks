package com.epam.hibernate.util;

import java.util.Locale;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public final class JpaUtil {
	static {
		try {
			LOGGER = Logger.getLogger(JpaUtil.class);
			Locale.setDefault(Locale.ENGLISH);
			JpaUtil.entityManagerFactory = Persistence
					.createEntityManagerFactory(JpaUtil.MANAGER_FACTORY_KEY);
		} catch (Throwable e) {
			JpaUtil.LOGGER.error(e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOGGER;
	private static EntityManagerFactory entityManagerFactory;
	private final static String MANAGER_FACTORY_KEY = "EmployeeJPA";

	private JpaUtil() {
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
