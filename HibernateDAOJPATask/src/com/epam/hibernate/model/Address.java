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
@Table(name="ADDRESS")
public class Address implements Serializable {
	private static final long serialVersionUID = 9047040297316966487L;
	private int id;
	private String street;
	private int house;
	private Integer housing;
	private int room;
	private Set<Office> officeSet = new HashSet<>();
	private City city;
	
	public Address() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ADDRESS_GEN") 
	@SequenceGenerator(name="ADDRESS_GEN", sequenceName = "ADDRESS_SEQ", allocationSize=1)
	@Column(name="ID", unique = true, nullable = false, precision = 10, scale = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="Street", nullable = false, length = 100)
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
	
	@Column(name="House", nullable = false, precision = 10, scale = 0)
	public int getHouse() {
		return house;
	}

	public void setHouse(int house) {
		this.house = house;
	}

	@Column(name="housing", nullable = true, precision = 10, scale = 0)
	public Integer getHousing() {
		return housing;
	}
	
	public void setHousing(Integer housing) {
		this.housing = housing;
	}

	@Column(name="Room", nullable = false, precision = 10, scale = 0)
	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = {CascadeType.ALL})
	public Set<Office> getOfficeSet() {
		return officeSet;
	}

	protected void setOfficeSet(Set<Office> officeSet) {
		this.officeSet = officeSet;
	}
	
	public void addOffice(Office office) {
		office.setAddress(this);
		officeSet.add(office);
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE })
	@JoinColumn(name = "CITY_ID", nullable = false)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", house=" + house
				+ ", housing=" + housing + ", room=" + room + ", city=" + city + "]";
	}
}
