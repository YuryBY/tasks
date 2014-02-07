package com.epam.hibernate.database.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.epam.hibernate.exeption.DAOTechnicException;
import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

/**
 * WrapperConnection: this class is wrapper for connection to database.
 * 
 * @author Sheiko Aliaksandr
 * @version 1.0 8 April 2013
 */
public final class WrapperConnection {
	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOGGER = Logger
			.getLogger(WrapperConnection.class);
	private static final String URL_KEY = "url";
	private static final String DRIVER_NAME_KEY = "driverName";
	private static final String CONNECTION_NULL_MESSAGE = "jdbc.connection.null";

	/** Contains connection to database */
	private Connection connection;

	/**
	 * Create connection to database and sets parameters from
	 * databases.properties
	 * 
	 * @throws DAOTechnicException
	 *             if SQLException was thrown.
	 */
	WrapperConnection(Properties properties) throws DAOTechnicException {
		Locale.setDefault(Locale.ENGLISH);
		String url = properties.getProperty(URL_KEY);
		String driverName = properties.getProperty(DRIVER_NAME_KEY);
		try {
			Class.forName(driverName);
			connection = (Connection) DriverManager.getConnection(url,
					properties);
		} catch (SQLException | ClassNotFoundException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
	}

	/**
	 * Create Statement type object using stored Connection object.
	 * 
	 * @return Statement type object.
	 * @throws DAOTechnicException
	 *             if field connection is null or SQLException occurs.
	 */
	public Statement getStatement() throws DAOTechnicException {
		if (connection != null) {
			Statement statement;
			try {
				statement = connection.createStatement();
				if (statement != null) {
					return statement;
				}
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
		throw new DAOTechnicException(PropertyManager.getProperty(
				CONNECTION_NULL_MESSAGE, BundleName.ERROR_MESSAGES));
	}

	/**
	 * Create PreparedStatement type object using stored Connection object.
	 * 
	 * @param query
	 *            is query to database.
	 * @return PreparedStatement type object.
	 * @throws DAOTechnicException
	 *             if field connection is null or SQLException occurs.
	 */
	public PreparedStatement getPreparedStatement(String query)
			throws DAOTechnicException {
		if (connection != null) {
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query);
				if (statement != null) {
					return statement;
				}
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
		throw new DAOTechnicException(PropertyManager.getProperty(
				CONNECTION_NULL_MESSAGE, BundleName.ERROR_MESSAGES));
	}

	public PreparedStatement getPreparedStatement(String query,
			String[] generatedKey) throws DAOTechnicException {
		if (connection != null) {
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query, generatedKey);
				if (statement != null) {
					return statement;
				}
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
		throw new DAOTechnicException(PropertyManager.getProperty(
				CONNECTION_NULL_MESSAGE, BundleName.ERROR_MESSAGES));
	}

	/**
	 * Closes given as parameter Statement type object.
	 * 
	 * @param statement
	 *            is Statement type object that should be closed.
	 * @throws DAOTechnicException
	 *             if SQLException occurs.
	 */
	public void closeStatement(Statement statement) throws DAOTechnicException {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
	}

	/**
	 * Closes connection stored as class field.
	 * 
	 * @throws DAOTechnicException
	 *             if SQLException occurs.
	 */
	void close() throws DAOTechnicException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
	}

	/**
	 * Return true if connection is closed or false.
	 * 
	 * @return true if connection is closed or false.
	 * @throws DAOTechnicException
	 *             if field connection is null or SQLException occurs.
	 */
	public boolean isClosed() throws DAOTechnicException {
		boolean closed = true;
		if (connection != null) {
			try {
				closed = connection.isClosed();
				return closed;
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
		throw new DAOTechnicException(PropertyManager.getProperty(
				CONNECTION_NULL_MESSAGE, BundleName.ERROR_MESSAGES));
	}

	/**
	 * Return true if autoCommit is true or false.
	 * 
	 * @return true if autoCommit is true or false.
	 * @throws DAOTechnicException
	 *             if field connection is null or SQLException occurs.
	 */
	public boolean getAutoCommit() throws DAOTechnicException {
		if (connection != null) {
			try {
				return connection.getAutoCommit();
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
		throw new DAOTechnicException(PropertyManager.getProperty(
				CONNECTION_NULL_MESSAGE, BundleName.ERROR_MESSAGES));
	}

	/**
	 * Commits the transaction in the connection.
	 * 
	 * @throws DAOTechnicException
	 *             if SQLException occurs.
	 */
	public void commit() throws DAOTechnicException {
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
	}

	/**
	 * Roll back to last commit state.
	 * 
	 * @throws DAOTechnicException
	 *             if SQLException occurs.
	 */
	public void rollback() throws DAOTechnicException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOTechnicException(e);
			}
		}
	}

	/**
	 * Establishes a connection to automate if autoCommit is true and vice
	 * versa.
	 * 
	 * @param autoCommit
	 *            is state auto commit of connection.
	 * @throws DAOTechnicException
	 */
	public void setAutoCommit(boolean autoCommit) throws DAOTechnicException {
		try {
			connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
	}
}
