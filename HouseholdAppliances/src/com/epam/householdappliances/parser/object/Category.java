package com.epam.householdappliances.parser.object;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Category {
	private Set<Subcategory> subcategoties;
	private String name;

	public Category() {
		subcategoties = new LinkedHashSet<>();
	}

	public Category(Set<Subcategory> categoryList) {
		this.subcategoties = categoryList;
	}

	public void addSubcategory(Subcategory subcategory) {
		subcategoties.add(subcategory);
	}

	public Iterator<Subcategory> iterator() {
		return subcategoties.iterator();
	}

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
		result = prime * result
				+ ((subcategoties == null) ? 0 : subcategoties.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (subcategoties == null) {
			if (other.subcategoties != null)
				return false;
		} else if (!subcategoties.equals(other.subcategoties))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [categoryList=" + subcategoties
				+ ", name=" + name + "]";
	}
}
