package com.epam.hibernate.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.hibernate.exeption.DAOTechnicException;
import com.epam.hibernate.model.Address;
import com.epam.hibernate.model.City;
import com.epam.hibernate.model.Company;
import com.epam.hibernate.model.Country;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.model.Job;
import com.epam.hibernate.model.Office;
import com.epam.hibernate.model.Position;

public final class ObjectJDBCCreator {
	private static final Logger LOGGER = Logger
			.getLogger(ObjectJDBCCreator.class);
	private static final String EMPLOYEE_ID = "employee_id";
	private static final String LASTNAME = "lastname";
	private static final String FIRSTNAME = "firstname";
	private static final String ADDRESS_HOME_ID = "address_home_id";
	private static final String ADDRESS_HOME_STREET = "address_home_street";
	private static final String ADDRESS_HOME_HOUSE = "address_home_house";
	private static final String ADDRESS_HOME_ROOM = "address_home_room";
	private static final String ADDRESS_HOME_HOUSING = "address_home_housing";
	private static final String CITY_HOME_ID = "city_home_id";
	private static final String CITY_HOME_NAME = "city_home_name";
	private static final String COUNTRY_HOME_ID = "country_home_id";
	private static final String COUNTRY_HOME_NAME = "country_home_name";
	private static final String JOB_ID = "job_id";
	private static final String POSITION_ID = "position_id";
	private static final String POSITION_NAME = "position_name";
	private static final String OFFICE_ID = "office_id";
	private static final String EMPLOYEE_IN_OFFICE = "employee_in_office";
	private static final String ADDRESS_WORK_ID = "address_work_id";
	private static final String ADDRESS_WORK_STREET = "address_work_street";
	private static final String ADDRESS_WORK_HOUSE = "address_work_house";
	private static final String ADDRESS_WORK_ROOM = "address_work_room";
	private static final String ADDRESS_WORK_HOUSING = "address_work_housing";
	private static final String CITY_WORK_ID = "city_work_id";
	private static final String CITY_WORK_NAME = "city_work_name";
	private static final String COUNTRY_WORK_ID = "country_work_id";
	private static final String COUNTRY_WORK_NAME = "country_work_name";
	private static final String COMPANY_ID = "company_id";
	private static final String COMPANY_NAME = "company_name";

	private ObjectJDBCCreator() {}

	public static Employee getEmployee(ResultSet resultSet) throws DAOTechnicException {
		Employee employee = new Employee();
		try {
			employee.setId(resultSet.getInt(EMPLOYEE_ID));
			employee.setLastName(resultSet.getString(LASTNAME));
			employee.setFirstName(resultSet.getString(FIRSTNAME));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return employee;
	}
	
	public static Employee getEmployeeWithIdOnly(ResultSet resultSet) throws DAOTechnicException {
		Employee employee = new Employee();
		try {
			employee.setId(resultSet.getInt(EMPLOYEE_ID));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return employee;
	}

	public static Address getHomeAddress(ResultSet resultSet) throws DAOTechnicException {
		Address homeAddress = new Address();
		try {
			homeAddress.setId(resultSet.getInt(ADDRESS_HOME_ID));
			homeAddress.setStreet(resultSet.getString(ADDRESS_HOME_STREET));
			homeAddress.setHouse(resultSet.getInt(ADDRESS_HOME_HOUSE));
			homeAddress.setRoom(resultSet.getInt(ADDRESS_HOME_ROOM));
			int housing = resultSet.getInt(ADDRESS_HOME_HOUSING);
			if (housing == 0) {
				homeAddress.setHousing(null);
			} else {
				homeAddress.setHousing(housing);
			}

			City homeCity = new City();
			homeCity.setId(resultSet.getInt(CITY_HOME_ID));
			homeCity.setName(resultSet.getString(CITY_HOME_NAME));
			homeCity.addAddress(homeAddress);

			Country homeCountry = new Country();
			homeCountry.setId(resultSet.getInt(COUNTRY_HOME_ID));
			homeCountry.setName(resultSet.getString(COUNTRY_HOME_NAME));
			homeCountry.addCity(homeCity);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return homeAddress;
	}

	public static Job getJob(ResultSet resultSet) throws DAOTechnicException {
		Job job;
		try {
			Position position = getPosition(resultSet);
			Office office = getOffice(resultSet);

			job = new Job();
			job.setId(resultSet.getInt(JOB_ID));
			job.setPosition(position);

			office.addJob(job);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return job;
	}

	public static Position getPosition(ResultSet resultSet) throws DAOTechnicException {
		Position position = new Position();
		try {
			position.setId(resultSet.getInt(POSITION_ID));
			position.setName(resultSet.getString(POSITION_NAME));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return position;
	}

	public static Office getOffice(ResultSet resultSet) throws DAOTechnicException {
		Office office ;
		try {
			Company company = getCompany(resultSet);
			Address workAddress = getWorkAddress(resultSet);
			
			office = new Office();
			office.setId(resultSet.getInt(OFFICE_ID));
			
			office.setEmployeeCount(resultSet.getInt(EMPLOYEE_IN_OFFICE));
			office.setCompany(company);

			office.setAddress(workAddress);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return office;
	}

	public static Address getWorkAddress(ResultSet resultSet) throws DAOTechnicException {
		Address workAddress;
		try {
			workAddress = new Address();
			workAddress.setId(resultSet.getInt(ADDRESS_WORK_ID));
			workAddress.setStreet(resultSet.getString(ADDRESS_WORK_STREET));
			workAddress.setHouse(resultSet.getInt(ADDRESS_WORK_HOUSE));
			workAddress.setRoom(resultSet.getInt(ADDRESS_WORK_ROOM));
			int workHousing = resultSet.getInt(ADDRESS_WORK_HOUSING);
			if (workHousing == 0) {
				workAddress.setHousing(null);
			} else {
				workAddress.setHousing(workHousing);
			}

			City workCity = new City();
			workCity.setId(resultSet.getInt(CITY_WORK_ID));
			workCity.setName(resultSet.getString(CITY_WORK_NAME));
			workCity.addAddress(workAddress);

			Country workCountry = new Country();
			workCountry.setId(resultSet.getInt(COUNTRY_WORK_ID));
			workCountry.setName(resultSet.getString(COUNTRY_WORK_NAME));
			workCountry.addCity(workCity);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return workAddress;
	}

	public static Company getCompany(ResultSet resultSet) throws DAOTechnicException {
		Company company;
		try {
			company = new Company();
			company.setId(resultSet.getInt(COMPANY_ID));
			company.setName(resultSet.getString(COMPANY_NAME));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return company;
	}
}
