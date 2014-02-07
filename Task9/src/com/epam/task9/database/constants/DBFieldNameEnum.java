package com.epam.task9.database.constants;

public enum DBFieldNameEnum {
	NEWS_ID("news_ID"), 
	TITLE("title"), 
	PUBLICATION_DATE("publication_date"), 
	DATE("date"),
	BRIEF("brief"), 
	CONTENT("content");

	private String fieldName;

	DBFieldNameEnum(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

}
