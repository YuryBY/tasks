package com.epam.hibernate.database;

import java.util.List;

import com.epam.hibernate.exeption.DAOTechnicException;
import com.epam.hibernate.model.Employee;

/**
 * AbstractDAO: generic abstract super class for all objects dao. Defines a set of abstract 
 * methods for working with database.
 * 
 * @author		Sheiko Aliaksandr
 * @version		1.0 8 April 2013
 */
public interface IEmployeeDAO {

	List<Employee> getList(int from,int count) throws DAOTechnicException;
	
	int size() throws DAOTechnicException;
}
