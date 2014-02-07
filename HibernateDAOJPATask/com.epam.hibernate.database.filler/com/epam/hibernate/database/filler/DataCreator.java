package com.epam.hibernate.database.filler;

import com.epam.hibernate.model.Address;
import com.epam.hibernate.model.City;
import com.epam.hibernate.model.Company;
import com.epam.hibernate.model.Country;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.model.Job;
import com.epam.hibernate.model.Office;
import com.epam.hibernate.model.Position;

public final class DataCreator {
	private DataCreator() {}
	
	public static Country getCountry(String name) {
		Country country = new Country();
		country.setName(name);
		return country;
	}
	
	public static City getCity(String name, Country country) {
		City city = new City();
		country.addCity(city);
		city.setName(name);
		return city;
	}
	
/*	public static Address getAddress(String name, City city) {
		Address address = new Address();
		Random random = new Random();
		address.setStreet(name + (random.nextInt(1_000) + 1));
		address.setHouse(random.nextInt(10_000) + 1);
		address.setRoom(random.nextInt(100_000) + 1);
		city.addAddress(address);
		return address;
	}*/
	
	public static Position getPosition(String name) {
		Position position = new Position();
		position.setName(name);
		return position;
	}
	
	public static Company getCompany(String name) {
		Company company = new Company();
		company.setName(name);
		return company;
	}
	
/*	public static Office getOffice(Company company, Address address) {
		Office office = new Office();
		company.addOffice(office);
		address.addOffice(office);
		return office;
	}*/

	public static Employee getEployee(Address address, int number) {
		/*Employee employee = new Employee();
		employee.setFirstName("First_Name_" + number);
		employee.setLastName("Last_Name_" + number);
		employee.setAddress(address);*/
		return null;
	}

	public static Job getJob(Position position, Office office) {
		Job job = new Job();
		job.setOffice(office);
		job.setPosition(position);
		return job;
	}
}
