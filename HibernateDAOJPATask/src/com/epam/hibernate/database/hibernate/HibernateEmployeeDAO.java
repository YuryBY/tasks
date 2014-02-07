package com.epam.hibernate.database.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.epam.hibernate.database.IEmployeeDAO;
import com.epam.hibernate.exeption.DAOTechnicException;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.util.HibernateUtil;

public final class HibernateEmployeeDAO implements IEmployeeDAO {
	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOGGER = Logger
			.getLogger(HibernateEmployeeDAO.class);
	private static final String ID_KEY = "id";

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getList(int from, int count)
			throws DAOTechnicException {
		Session session = null;
		List<Employee> employees = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setFirstResult(from);
			criteria.setMaxResults(count);
			criteria.addOrder(Order.asc(ID_KEY));
			employees = (List<Employee>) criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return employees;
	}

	@Override
	public int size() throws DAOTechnicException {
		Session session = null;
		int size;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long temp = (Long) session.createCriteria(Employee.class)
					.setProjection(Projections.rowCount()).uniqueResult();
			size = temp.intValue();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return size;
	}
}
