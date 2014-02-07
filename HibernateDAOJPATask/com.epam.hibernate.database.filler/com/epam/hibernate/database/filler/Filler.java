package com.epam.hibernate.database.filler;


public class Filler {
	/*private static final int COUNTRY_NUMBER = 10;
	private static final int CITY_NUMBER = 100;
	private static final int COMPANY_NUMBER = 40;
	private static final int JOB_NUMBER = 40_000;
	private static final int ADDRESS_COUNT = 10_000;
	private static final int EMPLOYEE_NUMBER = 10_000;
	private static final int EMPLOYEE_JOB_NUMBER = 4;
	private static final int OFFICE_COUNT = 600;
	private static final int POSITION_COUNT = 1_000;
	private static final Random RANDOM = new Random();*/

	public static void main(String[] args) {
		/*IEmployeeDAO employeeDAO =EmployeeDAOType.JPA.getEmployeeDAO();
		try {
			System.out.println(employeeDAO.getList(100).size());
			System.out.println(employeeDAO.getList(100));
		} catch (DAOTechnicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	@SuppressWarnings("unused")
	private static void fillAll() {
		//fillPosition();
		//fillCompany();
		//createJobFotEmployee();
		//fillAddress();
		//fillCity();
		//fillCountry();
		//fillJob();
		//fillEmployee();
		//fillOffice();
	}

/*	@SuppressWarnings("unused")
	private static void fillPosition() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= POSITION_COUNT; i++) {
				Position position = DataCreator.getPosition("Position_" + i);
				session.save(position);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void fillCompany() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= COMPANY_NUMBER; i++) {
				Company company = DataCreator.getCompany("Company_" + i);
				session.save(company);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void createEmployee() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= EMPLOYEE_NUMBER; i++) {
				Address address = (Address) session.load(Address.class, i);
				Employee employee = DataCreator.getEployee( address, i);
				session.save(employee);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void fillAddress() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= ADDRESS_COUNT; i++) {
				City city = (City) session.load(City.class, RANDOM.nextInt(CITY_NUMBER) + 1);
				Address address = DataCreator.getAddress("Street_", city);
				session.save(address);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void fillCity() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= CITY_NUMBER; i++) {
				Country country = (Country) session.load(Country.class, RANDOM.nextInt(COUNTRY_NUMBER) + 1);
				City city = DataCreator.getCity("City_" + i, country);
				session.save(city);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void fillCountry() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= COUNTRY_NUMBER; i++) {
				Country country = DataCreator.getCountry("Country_" + i);
				session.save(country);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unused")
	private static void fillOffice() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= OFFICE_COUNT; i++) {
				Company company = (Company) session.load(Company.class,
						RANDOM.nextInt(COMPANY_NUMBER) + 1);
				Address address = (Address) session.load(Address.class,
						RANDOM.nextInt(ADDRESS_COUNT) + 1);
				Office office = DataCreator.getOffice(company, address);
				session.save(office);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void createJobFotEmployee() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= EMPLOYEE_NUMBER; i++) {
				Employee employee = (Employee) session.load(Employee.class, i);
				{
					Job job = (Job) session.load(Job.class, 30_000 + i);
					employee.addJob(job);
				}
				int jobCount = RANDOM.nextInt(EMPLOYEE_JOB_NUMBER);
				for (int j = 1; j <= jobCount; j++) {
					Job job = (Job) session.load(Job.class,
							RANDOM.nextInt(EMPLOYEE_NUMBER) + (j - 1) * EMPLOYEE_NUMBER + 1);
					employee.addJob(job);
				}
				session.saveOrUpdate(employee);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private static void fillJob() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 1; i <= JOB_NUMBER; i++) {
				Office office = (Office) session.load(Office.class,
						RANDOM.nextInt(OFFICE_COUNT) + 1);
				Position position = (Position) session.load(Position.class,
						RANDOM.nextInt(POSITION_COUNT) + 1);
				Job job = DataCreator.getJob(position, office);
				session.save(job);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println(e.getMessage());
		}
	}*/
}
