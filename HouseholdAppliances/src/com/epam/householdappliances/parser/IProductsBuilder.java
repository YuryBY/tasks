package com.epam.householdappliances.parser;

import java.util.Set;

import com.epam.householdappliances.parser.object.Category;

public interface IProductsBuilder {
	
	abstract public Set<Category> buildCategoriesSet(String fileName);
}
