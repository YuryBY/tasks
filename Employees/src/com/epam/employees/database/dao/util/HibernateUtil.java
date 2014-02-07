package com.epam.employees.database.dao.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static final Logger LOG = Logger.getLogger(HibernateUtil.class);

	static {
		try {

			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (Throwable ex) {

			LOG.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
}