package com.epam.hibernate.database.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;

import com.epam.hibernate.database.IDAOFactory;
import com.epam.hibernate.database.IEmployeeDAO;
import com.epam.hibernate.util.HibernateUtil;
import com.epam.hibernate.util.ContextContainer;
import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

public final class HibernateDAOFactory implements IDAOFactory {
	private static final Logger LOGGER = Logger
			.getLogger(HibernateDAOFactory.class);
	private static final String EMPLOYEE_DAO = "hibernateEmployeeDAO";
	private static final String FACTORY_CLOSE_ERROR_MESSAGE = "hibernate.factory.close";

	@Override
	public IEmployeeDAO getEmployeeDAO() {
		ApplicationContext context = ContextContainer.SPRING_CONTEXT;
		return (IEmployeeDAO) context.getBean(EMPLOYEE_DAO);
	}

	@Override
	public void close() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		if (factory != null) {
			factory.close();
		} else {
			LOGGER.error(PropertyManager.getProperty(FACTORY_CLOSE_ERROR_MESSAGE,
					BundleName.ERROR_MESSAGES));
		}
	}
}
