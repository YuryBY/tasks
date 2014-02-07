package com.epam.employees.util.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.epam.employees.database.dao.util.HibernateUtil;
import com.epam.employees.exeption.TechnicalException;
import com.epam.employees.model.Address;
import com.epam.employees.model.City;
import com.epam.employees.model.Company;
import com.epam.employees.model.Country;
import com.epam.employees.model.Employee;
import com.epam.employees.model.Office;
import com.epam.employees.model.Job;
import com.epam.employees.model.Position;

public final class DBCreator {

	private static final Logger LOGGER = Logger.getLogger(DBCreator.class);

	/* Column names */
	// private static final String EMPLOYEE_FIRST_NAME = "First Name ";
	// private static final String EMPLOYEE_LAST_NAME = "Last name ";
	private static final String ADDRESS_STREET = "Address ";
	private static final String CITY_NAME = "City ";
	private static final String COUNTRY_NAME = "Country ";
	private static final String POSITION_NAME = "Position ";
	private static final String COMPANY_NAME = "Company ";

	/* Number of items */
	private static final int NUMBER_OF_COUNTRIES = 10000;
	private static final int NUMBER_OF_COMPANIES = 10000;
	private static final int NUMBER_OF_POSITIONS = 10000;
	private static final int NUMBER_OF_EMPLOYEES = 10000;
	private static final int NUMBER_OF_OFFICES = 10000;
	private static final int NUMBER_OF_JOBS = 10000;
	private static final int NUMBER_OF_ADDRESSES = 20000;
	private static final int NUMBER_OF_CITIES = 10000;

	private static final Random random;

	/*
	 * private IGenericDAO<Company> companyDAO; private IGenericDAO<Employee>
	 * employeeDAO; private IGenericDAO<Office> officeDAO; private
	 * IGenericDAO<City> cityDAO; private IGenericDAO<Address> addressDAO;
	 * private IGenericDAO<Country> countryDAO; private IGenericDAO<Position>
	 * positionDAO; private IGenericDAO<OfficeEmployee> officeEmployeeDAO;
	 */

	static {
		random = new Random();
	}

	public void fillDataBase() {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			List<Country> countries = fillCountryDAO();
			List<City> cities = fillCityDAO(countries);
			List<Address> addresses = fillAddressDAO(cities);
			List<Company> companies = fillCompanyDAO();
			List<Office> offices = fillOfficeDAO(companies, addresses);
			List<Position> positions = fillPositionDAO();
			List<Employee> employees = fillEmployeeDAO(addresses);
			fillJobDAO(offices, employees, positions);
			session.getTransaction().commit();

		} catch (TechnicalException e) {
			LOGGER.error(e);
		}
	}

	public void fillJobDAO(List<Office> offices, List<Employee> employees,
			List<Position> positions) throws TechnicalException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			for (int i = 0; i < NUMBER_OF_JOBS; i++) {
				Job job = new Job();
				session.beginTransaction();
				job.setEmployee(employees.get(random.nextInt(employees.size() - 1)));
				job.setOffice(offices.get(new Random().nextInt(offices.size() - 1)));
				job.setPosition(positions.get(new Random().nextInt(positions
						.size() - 1)));

				// jobDAO.save(job);
				session.save(job);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	private List<Office> fillOfficeDAO(List<Company> companies,
			List<Address> addresses) throws TechnicalException {
		Session session = null;

		session = HibernateUtil.getSessionFactory().openSession();
		List<Office> list = new LinkedList<>();
		for (int i = 0; i < NUMBER_OF_OFFICES; i++) {
			Office office = new Office();
			office.setCompany(companies.get(random.nextInt(companies.size() - 1)));
			office.setAddress(addresses.get(random.nextInt(addresses.size() - 1)));
			// office.setAddress(addresses.get(i));
			session.beginTransaction();
			// officeDAO.save(office);
			session.save(office);
			list.add(office);
			session.getTransaction().commit();
		}
		return list;
	}

	private List<Employee> fillEmployeeDAO(List<Address> addresses)
			throws TechnicalException {

		String[] firstNames = { "Abram", "Alexander", "Alexei", "Albert",
				"Anatoly", "Andrei", "Anton", "Arkady", "Arseny", "Artyom",
				"Artur", "Afanasy", "Bogdan", "Boris", "Vadim", "Valentin",
				"Valery", "Vasily", "Veniamin", "Viktor", "Vitaly", "Vlad",
				"Vladimir", "Vladislav", "Vsevolod", "Vyacheslav", "Gavriil",
				"Garry", "Gennady", "Georgy", "Gerasim", "German", "Gleb",
				"Grigory", "David", "Daniil", "Denis", "Dmitry", "Evgeny",
				"Yegor", "Yefim", "Zakhar", "Ivan", "Ignat", "Igor",
				"Illarion", "Ilia", "Immanuil", "Iosif", "Kirill",
				"Konstantin", "Lev", "Leonid", "Makar", "Maxim", "Marat",
				"Mark", "Matvei", "Mikhail", "Nestor", "Nikita", "Nikolay",
				"Oleg", "Pavel", "Peter", "Robert", "Rodion", "Roman",
				"Rostislav", "Ruslan", "Semyon", "Sergei", "Spartak",
				"Stanislav", "Stepan", "Taras", "Timofei", "Timur", "Trofim",
				"Eduard", "Erik", "Yulian", "Yury", "Yakov", "Yaroslav",
				"Alexandra", "Alina", "Alisa", "Alla", "Alyona", "Albina",
				"Anastasiya", "Anna", "Antonina", "Anzhelika", "Anfisa",
				"Vera", "Valeriya", "Varvara", "Vasilisa", "Vladlena",
				"Veronika", "Valentina", "Viktoriya", "Galina", "Darya",
				"Dina", "Diana", "Dominika", "Ekateirna", "Elena", "Elizaveta",
				"Evgeniya", "Eva", "Zhanna", "Zinaida", "Zoya", "Zlata",
				"Inga", "Inna", "Irina", "Inessa", "Izabella", "Izolda",
				"Iskra", "Klara", "Klavdiya", "Kseniya", "Kapitolina",
				"Klementina", "Kristina", "Lada", "Larisa", "Lidiya", "Lubov",
				"Liliya", "Ludmila", "Lucya", "Margarita", "Maya", "Malvina",
				"Marta", "Marina", "Mariya", "Nadezhda", "Natalya", "Nelly",
				"Nina", "Nika", "Nonna", "Oksana", "Olga", "Olesya", "Polina",
				"Raisa", "Rada", "Rozalina", "Regina", "Renata", "Svetlana",
				"Sofia", "Taisia", "Tamara", "Tatyana", "Ulyana", "Faina",
				"Fedosia", "Florentina", "Elvira", "Emilia", "Emma", "Yuliya",
				"Yaroslava", "Yana" };
		String[] lastNames = { "Abramov", "Adamovich", "Agin", "Aksamit",
				"Angeloff", "Antonoff", "Babin", "Babinski", "Balaban",
				"Banasik", "Baran", "Bardin", "Belinsky", "Belkin", "Below",
				"Belsky", "Berezin", "Bobko", "Bogdanovich", "Bolotin",
				"Borin", "Bunin", "Burak", "Burdin", "Chaban", "Chaplin",
				"Chaplinski", "Chernoff", "Churilla", "Daman", "Darin",
				"Davidoff", "Davidovich", "Davydov", "Dembinski", "Devin",
				"Dmitriev", "Dobrow", "Dolinski", "Dolinsky", "Dombrowsky",
				"Dorosh", "Duboff", "Dubow", "Dubsky", "Duskin", "Elin",
				"Eline", "Evanoff", "Falin", "Federoff", "Galkin", "Garin",
				"Genrich", "Glinka", "Golub", "Grasmick", "Guba", "Gura",
				"Gurin", "Gurkin", "Holub", "Ivanoff", "Ivanov", "Karp",
				"Kazan", "Korsak", "Kot", "Koval", "Kovalchik", "Kovaleski",
				"Kovalik", "Kovalski", "Kovalsky", "Kowalchuk", "Kowaleski",
				"Kozel", "Kozloff", "Kozlovsky", "Kozuch", "Kramar",
				"Krasinski", "Krasnoff", "Kravec", "Kravets", "Kristoff",
				"Krupski", "Kuzma", "Kuznetsov", "Lach", "Laskin", "Lazaroff",
				"Levin", "Levitsky", "Lipovsky", "Litvin", "Lopatin", "Lukin",
				"Markoff", "Markov", "Markovich", "Markow", "Markowski",
				"Maslow", "Medved", "Melnikoff", "Mihalovich", "Mikulich",
				"Minsky", "Mishkin", "Morein", "Moroz", "Novikoff", "Novosad",
				"Olshansky", "Orloff", "Orlov", "Orlovsky", "Ostrovsky", "Pac",
				"Paley", "Pavlov", "Pavlovich", "Pavlovsky", "Petroff",
				"Petrov", "Petrovich", "Petrowski", "Polakoff", "Polanski",
				"Polansky", "Poleski", "Polsky", "Polyakov", "Popoff", "Popov",
				"Popovich", "Pozniak", "Primack", "Prokop", "Pyzik",
				"Rakowski", "Rodin", "Roman", "Romanoff", "Romanowski",
				"Rudnitsky", "Rusak", "Russak", "Sadowski", "Samarin", "Savin",
				"Shimko", "Shostak", "Shubin", "Simonich", "Sliva", "Slivka",
				"Slutsky", "Slutzky", "Smolin", "Sokol", "Sokoloff", "Sokolov",
				"Sokolski", "Sorokin", "Stankevich", "Taras", "Topolski",
				"Tunick", "Tur", "Turchin", "Urban", "Volkov", "Wolsky",
				"Yudin", "Yurkovich", "Zaleski", "Zalesky", "Zaretsky",
				"Zlotnik" };

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		List<Employee> list = new LinkedList<>();

		Random randomGenerator = new Random();

		int firstNameslengt = firstNames.length;
		int lastNameslengt = lastNames.length;

		for (int i = 0; i < NUMBER_OF_EMPLOYEES; i++) {
			Employee employee = new Employee();

			int randomName = randomGenerator.nextInt(firstNameslengt);
			int randomLastName = randomGenerator.nextInt(lastNameslengt);

			employee.setFirstName(firstNames[randomName]);
			employee.setLastName(lastNames[randomLastName]);
			// employee.setAddress(addresses.get(i));
			employee.setAddress(addresses.get(random.nextInt(addresses.size() - 1)));
			session.beginTransaction();
			// employeeDAO.save(employee);
			session.save(employee);
			list.add(employee);
			session.getTransaction().commit();
		}
		return list;
	}

	private List<Address> fillAddressDAO(List<City> cities)
			throws TechnicalException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		List<Address> list = new LinkedList<>();
		for (int i = 0; i < NUMBER_OF_ADDRESSES; i++) {
			Address address = new Address();
			address.setStreet(ADDRESS_STREET + i);
			address.setCity(cities.get(random.nextInt(cities.size() - 1)));
			session.beginTransaction();
			// addressDAO.save(address);
			session.save(address);
			list.add(address);
			session.getTransaction().commit();
		}
		return list;
	}

	private List<City> fillCityDAO(List<Country> countries)
			throws TechnicalException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		List<City> list = new LinkedList<>();
		for (int i = 0; i < NUMBER_OF_CITIES; i++) {
			City city = new City();
			session.beginTransaction();
			city.setName(CITY_NAME + i);
			city.setCountry(countries.get(random.nextInt(countries.size() - 1)));
			// cityDAO.save(city);
			session.save(city);
			list.add(city);
			session.getTransaction().commit();
		}
		return list;
	}

	private List<Country> fillCountryDAO() throws TechnicalException {
		List<Country> list = new LinkedList<>();
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		for (int i = 0; i < NUMBER_OF_COUNTRIES; i++) {
			Country country = new Country();
			session.beginTransaction();
			country.setName(COUNTRY_NAME + i);
			// countryDAO.save(country);
			session.save(country);
			list.add(country);
			session.getTransaction().commit();
		}
		return list;
	}

	private List<Position> fillPositionDAO() throws TechnicalException {
		List<Position> list = new LinkedList<>();
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		for (int i = 0; i < NUMBER_OF_POSITIONS; i++) {
			Position position = new Position();
			session.beginTransaction();
			position.setName(POSITION_NAME + i);
			// positionDAO.save(position);
			session.save(position);
			list.add(position);
			session.getTransaction().commit();
		}
		return list;
	}

	private List<Company> fillCompanyDAO() throws TechnicalException {
		List<Company> list = new LinkedList<>();
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		for (int i = 0; i < NUMBER_OF_COMPANIES; i++) {
			Company company = new Company();
			company.setName(COMPANY_NAME + i);
			session.beginTransaction();
			// companyDAO.save(company);
			session.save(company);
			list.add(company);
			session.getTransaction().commit();
		}
		return list;
	}
}
