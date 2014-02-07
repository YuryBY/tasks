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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="COMPANY")
public class Company implements Serializable{
	private static final long serialVersionUID = 5565326048528348434L;
	private int id;
	private String name;
	private Set<Office> officeSet = new HashSet<>();
	
	public Company() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="COMPANY_GEN") 
	@SequenceGenerator(name="COMPANY_GEN", sequenceName = "COMPANY_SEQ", allocationSize=1)
	@Column(name="ID", unique = true, nullable = false, precision = 10, scale = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="Name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = {CascadeType.ALL})
	public Set<Office> getOfficeSet() {
		return officeSet;
	}

	protected void setOfficeSet(Set<Office> officeSet) {
		this.officeSet = officeSet;
	}
	
	public void addOffice(Office office) {
		office.setCompany(this);
		officeSet.add(office);
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
}
