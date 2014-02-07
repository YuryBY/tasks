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

import org.hibernate.annotations.Formula;

@Entity
@Table(name="OFFICE")
public class Office implements Serializable{
	private static final long serialVersionUID = 2548781259749058551L;
	private int id;
	private Address address;
	private Company company;
	private int employeeCount;
	private Set<Job> jobSet = new HashSet<>();
	
	public Office() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="OFFICE_GEN") 
	@SequenceGenerator(name="OFFICE_GEN", sequenceName = "OFFICE_SEQ", allocationSize=1)
	@Column(name="ID", unique = true, nullable = false, precision = 10, scale = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE })
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "office")
	public Set<Job> getJobSet() {
		return jobSet;
	}

	protected void setJobSet(Set<Job> jobSet) {
		this.jobSet = jobSet;
	}
	
	public void addJob(Job job) {
		job.setOffice(this);
		jobSet.add(job);
	}

	@Formula(value = "(SELECT COUNT(*) FROM JOB WHERE JOB.OFFICE_ID=ID)")
	public int getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
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
		Office other = (Office) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Office [id=" + id + ", address=" + address + ", company="
				+ company +  ", employeeCount=" + employeeCount + "]";
	}
}
