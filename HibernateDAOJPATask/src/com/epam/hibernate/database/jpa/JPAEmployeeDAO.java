package com.epam.hibernate.database.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;

import com.epam.hibernate.database.IEmployeeDAO;
import com.epam.hibernate.exeption.DAOTechnicException;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.util.JpaUtil;
import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

public final class JPAEmployeeDAO implements IEmployeeDAO {
	private static final Logger LOGGER = Logger.getLogger(JPAEmployeeDAO.class);
	private static final String MANAGER_ERROR_CLOSE_MESSAGE = "jpa.entitymanager.null";
	private static final String HSQL_SELECT_EMPLOYEE = PropertyManager
			.getProperty("employee.select.all", BundleName.HSQL_QUERY);

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getList(int from, int count)
			throws DAOTechnicException {
		EntityManager entityManager = null;
		try {
			entityManager = JpaUtil.getEntityManagerFactory()
					.createEntityManager();
			Query query = entityManager.createQuery(HSQL_SELECT_EMPLOYEE);
			query.setFirstResult(from);
			query.setMaxResults(count);
			List<Employee> employeeList = (List<Employee>) query
					.getResultList();
			return employeeList;
		} catch(Error e){
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		} finally {
			closeEntityManager(entityManager);
		}
	}

	@Override
	public int size() throws DAOTechnicException {
		EntityManager entityManager = null;
		try {
			entityManager = JpaUtil.getEntityManagerFactory()
					.createEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Long> criteria = criteriaBuilder
					.createQuery(Long.class);
			criteria.select(criteriaBuilder.count(criteria.from(Employee.class)));
			Long count = entityManager.createQuery(criteria).getSingleResult();
			return count.intValue();
		} catch(Error e){
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}  finally {
			closeEntityManager(entityManager);
		}
	}

	private void closeEntityManager(EntityManager entityManager)
			throws DAOTechnicException {
		if (entityManager != null) {
			entityManager.close();
		} else {
			LOGGER.error(PropertyManager.getProperty(MANAGER_ERROR_CLOSE_MESSAGE,
					BundleName.ERROR_MESSAGES));
		}
	}
}
