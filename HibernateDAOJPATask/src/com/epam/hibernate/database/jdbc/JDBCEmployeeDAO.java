package com.epam.hibernate.database.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.hibernate.database.IEmployeeDAO;
import com.epam.hibernate.database.jdbc.connection.IConnectionPool;
import com.epam.hibernate.database.jdbc.connection.WrapperConnection;
import com.epam.hibernate.exeption.DAOTechnicException;
import com.epam.hibernate.model.Address;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.model.Job;
import com.epam.hibernate.util.ObjectJDBCCreator;
import com.epam.hibernate.util.resource.BundleName;
import com.epam.hibernate.util.resource.PropertyManager;

public final class JDBCEmployeeDAO implements IEmployeeDAO {
	private static final Logger LOGGER = Logger
			.getLogger(JDBCEmployeeDAO.class);
	private final String COUNT_KEY = "count";
	private final String EMPLOYEE_LOST_MESSAGE = "employee.lost";
	private final String SQL_LIMITED_EMPLOYEE_PART_1 = PropertyManager.getProperty(
			"employee.select.limited.part1", BundleName.SQL_QUERY);
	private final String SQL_LIMITED_EMPLOYEE_PART_2 = PropertyManager.getProperty(
			"employee.select.limited.part2", BundleName.SQL_QUERY);
	private final String SQL_LIMITED_EMPLOYEE_INFORMATION = PropertyManager
			.getProperty("employee.select.limited.information", BundleName.SQL_QUERY);
	private final String SQL_EMPLOYEE_COUNT = PropertyManager.getProperty(
			"employee.count", BundleName.SQL_QUERY);

	private IConnectionPool connectionPool;

	public JDBCEmployeeDAO(IConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}

	@Override
	public List<Employee> getList(int from, int count)
			throws DAOTechnicException {
		final String SQL_LIMITED_EMPLOYEE_LIST = SQL_LIMITED_EMPLOYEE_PART_1
				+ (from + count) + SQL_LIMITED_EMPLOYEE_PART_2 + from;
		WrapperConnection connection = connectionPool.getConnection();
		List<Employee> employeeList;
		Statement st = null;
		try {
			st = connection.getStatement();
			ResultSet resultSet = st.executeQuery(SQL_LIMITED_EMPLOYEE_LIST);
			employeeList = fillOnlyEmployee(resultSet);
			final String SQL_GIVEN_EMPLOYEE_INFORMATION = SQL_LIMITED_EMPLOYEE_INFORMATION
					+ createInList(employeeList);
			resultSet = st.executeQuery(SQL_GIVEN_EMPLOYEE_INFORMATION);
			fillEmployeeProperty(resultSet, employeeList);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		} finally {
			connection.closeStatement(st);
			connectionPool.releseConnection(connection);
		}
		return employeeList;
	}

	@Override
	public int size() throws DAOTechnicException {
		WrapperConnection connection = connectionPool.getConnection();
		Statement st = null;
		try {
			st = connection.getStatement();
			ResultSet resultSet = st.executeQuery(SQL_EMPLOYEE_COUNT);
			resultSet.next();
			return resultSet.getInt(COUNT_KEY);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		} finally {
			connection.closeStatement(st);
			connectionPool.releseConnection(connection);
		}
	}

	private List<Employee> fillOnlyEmployee(ResultSet resultSet)
			throws DAOTechnicException {
		List<Employee> employeeList = new LinkedList<>();
		try {
			while (resultSet.next()) {
				Employee employee = ObjectJDBCCreator.getEmployee(resultSet);
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return employeeList;
	}

	private List<Employee> fillEmployeeProperty(ResultSet resultSet,
			List<Employee> employeeList) throws DAOTechnicException {
		try {
			while (resultSet.next()) {
				Employee employeeId = ObjectJDBCCreator
						.getEmployeeWithIdOnly(resultSet);
				if (employeeList.contains(employeeId)) {
					int employeeExistIndex = employeeList.indexOf(employeeId);
					Employee employee = employeeList.get(employeeExistIndex);
					Job job = ObjectJDBCCreator.getJob(resultSet);
					if(employee.getAddress() == null) {
						Address homeAddress = ObjectJDBCCreator
								.getHomeAddress(resultSet);
						employee.setAddress(homeAddress);
					}
					employee.addJob(job);
				} else {
					LOGGER.error(PropertyManager.getProperty(EMPLOYEE_LOST_MESSAGE,
							BundleName.ERROR_MESSAGES));
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOTechnicException(e);
		}
		return employeeList;
	}

	private String createInList(List<Employee> employeeList) {
		StringBuilder inEmployeeList = new StringBuilder();
		inEmployeeList.append("(");
		for (Employee employee : employeeList) {
			inEmployeeList.append(employee.getId() + ",");
		}
		inEmployeeList.deleteCharAt(inEmployeeList.length() - 1);
		inEmployeeList.append(")");
		return inEmployeeList.toString();
	}
}
