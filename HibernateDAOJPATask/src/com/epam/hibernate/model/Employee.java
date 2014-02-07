package com.epam.hibernate.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable{
	private static final long serialVersionUID = -8076312917573877893L;
	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	private Set<Job> jobSet = new HashSet<>();
	
	public Employee() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="EMPLOYEE_GEN") 
	@SequenceGenerator(name="EMPLOYEE_GEN", sequenceName = "EMPLOYEE_SEQ", allocationSize=1)
	@Column(name="ID", unique = true, nullable = false, precision = 10, scale = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="FirstName", nullable = false, length = 100)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LastName", nullable = false, length = 100)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID", unique = true, nullable = false)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@BatchSize(size = 100)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
	public Set<Job> getJobSet() {
		return jobSet;
	}

	protected void setJobSet(Set<Job> jobSet) {
		this.jobSet = jobSet;
	}
	
	public void addJob(Job job) {
		job.setEmployee(this);
		jobSet.add(job);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", jobSet=" + jobSet + ", address=" + address + "]";
	}
}
