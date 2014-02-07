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
@Table(name="COUNTRY")
public class Country implements Serializable{
	private static final long serialVersionUID = -4606101913289820035L;
	private int id;
	private String name;
	private Set<City> citySet = new HashSet<>();
	
	public Country() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="COYNTRY_GEN") 
	@SequenceGenerator(name="COYNTRY_GEN", sequenceName = "COYNTRY_SEQ", allocationSize=1)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.ALL})
	public Set<City> getCitySet() {
		return citySet;
	}

	protected void setCitySet(Set<City> citySet) {
		this.citySet = citySet;
	}
	
	public void addCity(City city) {
		city.setCountry(this);
		citySet.add(city);
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
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
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
