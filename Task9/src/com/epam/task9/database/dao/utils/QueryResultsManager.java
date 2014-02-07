package com.epam.task9.database.dao.utils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.task9.database.constants.DBFieldNameEnum;

public class QueryResultsManager {

	public static int getNewsId(ResultSet resultSet) throws SQLException {
		return resultSet.getInt(DBFieldNameEnum.NEWS_ID.getFieldName());
	}

	public static String getNewsTitle(ResultSet resultSet) throws SQLException {
		return resultSet.getString(DBFieldNameEnum.TITLE.getFieldName());
	}

	public static Date getNewsDate(ResultSet resultSet) throws SQLException {
		return resultSet.getDate(DBFieldNameEnum.PUBLICATION_DATE
				.getFieldName());
	}

	public static String getBriefNews(ResultSet resultSet) throws SQLException {
		return resultSet.getString(DBFieldNameEnum.BRIEF.getFieldName());
	}

	public static String getNewsContent(ResultSet resultSet)
			throws SQLException {
		return resultSet.getString(DBFieldNameEnum.CONTENT.getFieldName());
	}

}
