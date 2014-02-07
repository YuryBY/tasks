package com.epam.hibernate.database.jdbc;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.epam.hibernate.database.IDAOFactory;
import com.epam.hibernate.database.IEmployeeDAO;
import com.epam.hibernate.util.ContextContainer;
import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

public final class JDBCEmpoyeeDAOFactory implements IDAOFactory {
	private static final Logger LOGGER = Logger
			.getLogger(JDBCEmpoyeeDAOFactory.class);
	private static final String EMPLOYEE_DAO_KEY = "jdbcEmployeeDAO";
	private static final String POOL_CLOSE_MESSAGE = "jdbc.connection.close";

	@Override
	public IEmployeeDAO getEmployeeDAO() {
		ApplicationContext context = ContextContainer.SPRING_CONTEXT;
		return (IEmployeeDAO) context.getBean(EMPLOYEE_DAO_KEY);
	}

	@Override
	public void close() {
		LOGGER.info(PropertyManager.getProperty(POOL_CLOSE_MESSAGE,
				BundleName.ERROR_MESSAGES));
	}

}
