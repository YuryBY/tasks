package com.epam.hibernate.database.jdbc.connection;

import com.epam.hibernate.exeption.DAOTechnicException;

public interface IConnectionPool {

	void releseConnection(WrapperConnection connection) throws DAOTechnicException;

	WrapperConnection getConnection() throws DAOTechnicException;

	void close();
}
