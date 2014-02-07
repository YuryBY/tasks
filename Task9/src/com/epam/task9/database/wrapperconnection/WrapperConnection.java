package com.epam.task9.database.wrapperconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.task9.exeption.TechnicalException;

public class WrapperConnection {
	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger.getLogger(WrapperConnection.class);

	/** Contains connection to database */
	private Connection connection;

	/**
	 * Create connection to database and sets parameters from
	 * databases.properties
	 * 
	 * @throws TechnicalException
	 *             if SQLException was thrown.
	 */
	public WrapperConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Create Statement type object using stored Connection object.
	 * 
	 * @return Statement type object.
	 * @throws TechnicalException
	 *             if field connection is null or SQLException occurs.
	 */
	public Statement getStatement() throws TechnicalException {
		if (connection != null) {
			Statement statement;
			try {
				statement = connection.createStatement();
				if (statement != null) {
					return statement;
				}
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
		throw new TechnicalException("connection is null!");
		/*
		 * PropertyManager.getProperty("null.connection",
		 * "resources.errorMessages"));
		 */
	}

	/**
	 * Create PreparedStatement type object using stored Connection object.
	 * 
	 * @param query
	 *            is query to database.
	 * @return PreparedStatement type object.
	 * @throws TechnicalException
	 *             if field connection is null or SQLException occurs.
	 */
	public PreparedStatement getPreparedStatement(String query)
			throws TechnicalException {
		if (connection != null) {
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query);
				if (statement != null) {
					return statement;
				}
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
		throw new TechnicalException("null.connection");
		/*
		 * PropertyManager.getProperty("null.connection",
		 * "resources.errorMessages"));
		 */
	}

	public PreparedStatement getPreparedStatement(String query,
			String[] generatedKey) throws TechnicalException {
		if (connection != null) {
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query, generatedKey);
				if (statement != null) {
					return statement;
				}
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
		throw new TechnicalException("null.connection");
	}

	/**
	 * Closes given as parameter Statement type object.
	 * 
	 * @param statement
	 *            is Statement type object that should be closed.
	 * @throws TechnicalException
	 *             if SQLException occurs.
	 */
	public void closeStatement(Statement statement) throws TechnicalException {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
	}

	/**
	 * Closes connection stored as class field.
	 * 
	 * @throws TechnicalException
	 *             if SQLException occurs.
	 */
	public void close() throws TechnicalException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
	}

	/**
	 * Return true if connection is closed or false.
	 * 
	 * @return true if connection is closed or false.
	 * @throws TechnicalException
	 *             if field connection is null or SQLException occurs.
	 */
	public boolean isClosed() throws TechnicalException {
		boolean closed = true;
		if (connection != null) {
			try {
				closed = connection.isClosed();
				return closed;
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
		throw new TechnicalException("null.connection");
		/*
		 * PropertyManager.getProperty("null.connection",
		 * "resources.errorMessages"));
		 */
	}

	/**
	 * Return true if autoCommit is true or false.
	 * 
	 * @return true if autoCommit is true or false.
	 * @throws TechnicalException
	 *             if field connection is null or SQLException occurs.
	 */
	public boolean getAutoCommit() throws TechnicalException {
		if (connection != null) {
			try {
				return connection.getAutoCommit();
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
		throw new TechnicalException("null.connection");
		/*
		 * PropertyManager.getProperty("null.connection",
		 * "resources.errorMessages"));
		 */
	}

	/**
	 * Commits the transaction in the connection.
	 * 
	 * @throws TechnicalException
	 *             if SQLException occurs.
	 */
	public void commit() throws TechnicalException {
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
	}

	/**
	 * Roll back to last commit state.
	 * 
	 * @throws TechnicalException
	 *             if SQLException occurs.
	 */
	public void rollback() throws TechnicalException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				LOG.error(e);
				throw new TechnicalException(e);
			}
		}
	}

	/**
	 * Establishes a connection to automate if autoCommit is true and vice
	 * versa.
	 * 
	 * @param autoCommit
	 *            is state auto commit of connection.
	 * @throws TechnicalException
	 */
	public void setAutoCommit(boolean autoCommit) throws TechnicalException {
		try {
			connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			LOG.error(e);
			throw new TechnicalException(e);
		}
	}
}
