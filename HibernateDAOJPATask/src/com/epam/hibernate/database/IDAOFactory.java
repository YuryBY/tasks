package com.epam.hibernate.database;

public interface IDAOFactory {
	IEmployeeDAO getEmployeeDAO();
	
	void close();
}
