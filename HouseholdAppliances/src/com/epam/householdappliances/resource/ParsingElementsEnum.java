package com.epam.householdappliances.resource;

public enum ParsingElementsEnum {
	PRODUCTS("products"),
	CATEGORY("category"),
	SUBCATEGORY("subcategory"),
	PRODUCT("product"),
	NAME_ATTRIBUTE("name"),
	PRODUCER("producer"),
	MODEL("model"),
	DATE_ISSUE("date-issue"),
	COLOR("color"),
	PRICE("price"),
	MISS("miss"),
	
	TNS_CATEGORY("tns:category"),
	TNS_SUBCATEGORY("tns:subcategory"),
	TNS_PRODUCT("tns:product"),
	TNS_PRODUCER("tns:producer"),
	TNS_MODEL("tns:model"),
	TNS_DATE("tns:date-issue"),
	TNS_COLOR("tns:color"),
	TNS_PRICE("tns:price"),
	TNS_MISS("tns:miss");
	
	private final String value;
	
	private ParsingElementsEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
