package com.epam.employees.database.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.epam.employees.database.dao.IDAO;
import com.epam.employees.exeption.TechnicalException;
import com.epam.employees.resource.DBQueryConstants;

@Transactional(rollbackFor = Exception.class)
public class AbstractDAO<T> implements IDAO<T> {

	private static final Logger LOG = Logger.getLogger(AbstractDAO.class);

	private Class<T> persistentClass;
	private static SessionFactory sessionFactory;

	public AbstractDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		AbstractDAO.sessionFactory = sessionFactory;
	}

	protected static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/** @Override */
	public List<T> getAll() throws TechnicalException {
		return null;
	}

	@SuppressWarnings("unchecked")
	/**@Override*/
	public List<T> getDefinedQuantity(int firstResult, int maxResults)
			throws TechnicalException {
		try {
			Session session = getCurrentSession();
			Query q = session.createQuery(DBQueryConstants.FROM
					+ persistentClass.getName());
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResults);
			List<T> list = q.list();
			return list;
		} catch (HibernateException e) {
			LOG.error(e);
			throw new TechnicalException(e);
		}
	}

	/** @Override */
	public T fetchById(long id) throws TechnicalException {
		return null;
	}

	/** @Override */
	public T update(T item) throws TechnicalException {
		return null;
	}

	/** @Override */
	public boolean delete(T item) throws TechnicalException {
		return true;
	}

	/** @Override */
	public T save(T item) throws TechnicalException {
		return null;
	}

}