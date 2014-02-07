package com.epam.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="POSITION")
public class Position implements Serializable{
	private static final long serialVersionUID = -1554990960825503213L;
	private int id;
	private String name;
	
	public Position() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="POSITION_GEN") 
	@SequenceGenerator(name="POSITION_GEN", sequenceName = "POSITION_SEQ", allocationSize=1)
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
		Position other = (Position) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", name=" + name + "]";
	}
}
