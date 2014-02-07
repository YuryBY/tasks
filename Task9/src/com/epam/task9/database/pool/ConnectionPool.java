package com.epam.task9.database.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.epam.task9.database.wrapperconnection.WrapperConnection;
import com.epam.task9.exeption.TechnicalException;

public class ConnectionPool {// implements IConnectionPool {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

	/** Single instance of ConnectionPool type object. */
	private static ConnectionPool instance = null;

	/** Lock prevents simultaneous recourse to the backlog queue. */
	private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();

	/** free connections queue */
	private BlockingQueue<WrapperConnection> connectionQueue;

	private String url;
	private String user;
	private String password;
	private String driver;
	private int poolSize;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getDriver() {
		return driver;
	}

	public int getPoolSize() {
		return poolSize;
	}

	private ConnectionPool() {
	}

	/**
	 * @throws TechnicalException
	 * @throws SQLException
	 */
	public void init() throws TechnicalException, SQLException {
		WrapperConnection connection;
		connectionQueue = new ArrayBlockingQueue<WrapperConnection>(poolSize);

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new TechnicalException(e);
		}

		for (int i = 0; i < poolSize; i++) {
			try {
				connection = newConnection();
				connectionQueue.offer(connection);
			} catch (TechnicalException e) {
				LOG.error(e);
			}
		}

		if (connectionQueue.size() == 0) {
			throw new TechnicalException("pool is empty!");
		}

	}

	/**
	 * @return tag
	 * @throws SQLException
	 *             tag
	 * @throws TechnicalException
	 */

	private WrapperConnection newConnection() throws SQLException,
			TechnicalException {
		WrapperConnection wrapperConnection;

		Locale.setDefault(Locale.ENGLISH);
		Connection connection = DriverManager
				.getConnection(url, user, password);

		wrapperConnection = new WrapperConnection(connection);

		return wrapperConnection;
	}

	/**
	 * Returns (or creates before) ConnectionPool object.
	 * 
	 * @return ConnectionPool object.
	 * @throws TechnicalException
	 */
	public static ConnectionPool getInstance() throws TechnicalException {
		if (instance == null) {
			INSTANCE_LOCK.lock();
			try {
				if (instance == null) {
					instance = new ConnectionPool();
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
	 * @throws TechnicalException
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
	 * @throws TechnicalException
	 *             if connection closed or can't be added to queue.
	 * @throws SQLException
	 */
	public void releaseConnection(WrapperConnection connection) {
		connectionQueue.offer(connection);
	}

	/**
	 * Closes all connections in pool ad commit them before (if autoCommit is
	 * false).
	 */
	public void dispose() {
		INSTANCE_LOCK.lock();
		try {
			WrapperConnection connection;
			while ((connection = connectionQueue.poll()) != null) {
				try {
					if (!connection.getAutoCommit()) {
						connection.commit();
					}
					connection.close();
				} catch (TechnicalException e) {
					LOG.error(e);
				}
			}
		} finally {
			INSTANCE_LOCK.unlock();
		}
	}
}