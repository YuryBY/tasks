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

@Entity
@Table(name="CITY")
public class City implements Serializable{
	private static final long serialVersionUID = -3374984896581713853L;
	private int id;
	private String name;
	private Set<Address> addressSet = new HashSet<>();
	private Country country;
	
	public City() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="CITY_GEN") 
	@SequenceGenerator(name="CITY_GEN", sequenceName = "CITY_SEQ", allocationSize=1)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = {CascadeType.ALL})
	public Set<Address> getAddressSet() {
		return addressSet;
	}

	protected void setAddressSet(Set<Address> addressSet) {
		this.addressSet = addressSet;
	}
	
	public void addAddress(Address address) {
		address.setCity(this);
		addressSet.add(address);
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE })
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
		City other = (City) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", country=" + country
				+ "]";
	}
}
