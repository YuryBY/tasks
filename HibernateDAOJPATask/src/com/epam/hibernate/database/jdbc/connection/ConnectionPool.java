package com.epam.hibernate.database.jdbc.connection;

import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.epam.hibernate.exeption.DAOTechnicException;
import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

/**
 * ConnectionPool create and manage pool of database connections. This class
 * realize Singleton pattern.
 * 
 * @author Sheiko Aliaksandr
 * @version 1.0 8 April 2013
 */
public final class ConnectionPool implements IConnectionPool {
	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
	private static final int POOL_DEFAULT_SIZE = 10;
	private static final String POOL_SIZE_KEY = "poolSize";
	private static final String POOL_EMPTY_MESSAGE = "pool.empty";

	/** Single instance of ConnectionPool type object. */
	private static ConnectionPool instance = null;

	/** Lock prevents simultaneous recourse to the backlog queue. */
	private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();

	/** free connections queue */
	private ArrayBlockingQueue<WrapperConnection> connectionQueue;
	private ArrayBlockingQueue<WrapperConnection> connectionCloseQueue;

	/**
	 * Create connection pool using.
	 * 
	 * @throws SQLException
	 *             some sql exception at pool creation time
	 * @throws DAOTechnicException
	 */
	private ConnectionPool(String filePath) throws DAOTechnicException {
		WrapperConnection connection;
		Properties connectionProperty = PropertyManager.getProperties(filePath);
		int poolSize = POOL_DEFAULT_SIZE;
		if (connectionProperty.containsKey(POOL_SIZE_KEY)) {
			poolSize = Integer.parseInt(connectionProperty
					.getProperty(POOL_SIZE_KEY));
		}
		connectionQueue = new ArrayBlockingQueue<WrapperConnection>(poolSize);
		connectionCloseQueue = new ArrayBlockingQueue<WrapperConnection>(
				poolSize);
		for (int i = 0; i < poolSize; i++) {
			try {
				connection = new WrapperConnection(connectionProperty);
				connectionQueue.offer(connection);
			} catch (DAOTechnicException e) {
				LOGGER.error(e);
			}
		}
		connectionCloseQueue.addAll(connectionQueue);
		if (connectionQueue.size() == 0) {
			throw new DAOTechnicException(PropertyManager.getProperty(
					POOL_EMPTY_MESSAGE, BundleName.ERROR_MESSAGES));
		}
	}

	/**
	 * Returns (or creates before) ConnectionPool object.
	 * 
	 * @return ConnectionPool object.
	 * @throws DAOTechnicException
	 */
	public static ConnectionPool getInstance(String filePath)
			throws DAOTechnicException {
		if (instance == null) {
			INSTANCE_LOCK.lock();
			try {
				if (instance == null) {
					instance = new ConnectionPool(filePath);
				}
			} finally {
				INSTANCE_LOCK.unlock();
			}
		}
		return instance;
	}

	/**
	 * Gives a connection from the pool. Waiting if pool is empty.
	 * 
	 * @return free connection
	 * @throws DAOTechnicException
	 * @throws InterruptedException
	 *             if waiting interrupts
	 */
	public WrapperConnection getConnection() {
		WrapperConnection connection = connectionQueue.poll();
		return connection;
	}

	/**
	 * Returns connection back to the pool
	 * 
	 * @param connection
	 *            no more needed connection
	 * @throws DAOTechnicException
	 *             if connection closed or can't be added to queue.
	 * @throws SQLException
	 */
	public void releseConnection(WrapperConnection connection) {
		connectionQueue.offer(connection);
	}

	/**
	 * Closes all connections in pool ad commit them before (if autoCommit is
	 * false).
	 */
	public void close() {
		INSTANCE_LOCK.lock();
		try {
			WrapperConnection connection;
			while ((connection = connectionCloseQueue.poll()) != null) {
				try {
					if (!connection.getAutoCommit()) {
						connection.commit();
					}
					connection.close();
				} catch (DAOTechnicException e) {
					LOGGER.error(e);
				}
			}
		} finally {
			INSTANCE_LOCK.unlock();
		}
	}
}