package com.epam.hibernate.test;

import java.util.List;

import com.epam.hibernate.database.IEmployeeDAO;
import com.epam.hibernate.database.jdbc.JDBCEmployeeDAO;
import com.epam.hibernate.database.jdbc.connection.ConnectionPool;
import com.epam.hibernate.exeption.DAOTechnicException;
import com.epam.hibernate.model.Employee;


public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ConnectionPool pool = ConnectionPool.getInstance("resources.jdbc");
			IEmployeeDAO emplDAO = new JDBCEmployeeDAO(pool);
			//IEmployeeDAO emplDAO = new HibernateEmployeeDAO();
			//IEmployeeDAO emplDAO = new JPAEmployeeDAO();
			List<Employee> empList = emplDAO.getList(100,30);
			for(Employee emp: empList) {
				System.out.print(emp.getId() + " ");
			}
			System.out.println(empList.size());
			/*List<Employee> empList2 = emplDAO.getList(0,20);
			for(Employee emp: empList2) {
				System.out.print(emp.getId() + " ");
			}
			System.out.println(empList2.size());
			List<Employee> empList3 = emplDAO.getList(40,20);
			for(Employee emp: empList3) {
				System.out.print(emp.getId() + " ");
			}
			System.out.println(empList3.size());*/
		} catch (DAOTechnicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
