package com.epam.hibernate.util;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public final class HibernateUtil {
	static {
		try {
			Locale.setDefault(Locale.ENGLISH);
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			HibernateUtil.sessionFactory = configuration
					.buildSessionFactory(serviceRegistry);
		} catch (Throwable e) {
			HibernateUtil.LOGGER.error(e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
