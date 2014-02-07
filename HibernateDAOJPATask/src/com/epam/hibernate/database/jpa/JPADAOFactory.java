package com.epam.hibernate.database.jpa;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.epam.hibernate.database.IDAOFactory;
import com.epam.hibernate.database.IEmployeeDAO;
import com.epam.hibernate.util.JpaUtil;
import com.epam.hibernate.util.ContextContainer;
import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

public final class JPADAOFactory implements IDAOFactory {
	private static final Logger LOGGER = Logger.getLogger(JPADAOFactory.class);
	private static final String EMPLOYEE_DAO_KEY = "jpaEmployeeDAO";
	private static final String FACTORY_CLOSE_ERROR_MESSAGE = "jpa.factory.close";

	@Override
	public IEmployeeDAO getEmployeeDAO() {
		ApplicationContext context = ContextContainer.SPRING_CONTEXT;
		return (IEmployeeDAO) context.getBean(EMPLOYEE_DAO_KEY);
	}

	@Override
	public void close() {
		EntityManagerFactory factory = JpaUtil.getEntityManagerFactory();
		if (factory != null) {
			factory.close();
		} else {
			LOGGER.error(PropertyManager.getProperty(FACTORY_CLOSE_ERROR_MESSAGE,
					BundleName.ERROR_MESSAGES));
		}
	}

}
