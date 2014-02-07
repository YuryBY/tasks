package com.epam.task9.database.constants;


public enum DBQueryEnum {
			SELECT_ALL_NEWS(
			"SELECT * from NewsFeed ORDER BY NewsFeed.publication_date DESC"),
			SELECT_NEWS_BY_ID(
			"SELECT title, publication_date, brief, content FROM NewsFeed "
			+ "WHERE news_ID=?"), 
			CREATE_NEWS(
			"INSERT INTO NewsFeed(news_id,title, publication_date, brief, content) "
			+ "VALUES(?,?, ?, ?, ?)"),
			GET_LAST_ADDED(
			"SELECT MAX(news_ID) FROM NewsFeed"),
			UPDATE_NEWS_BY_ID(
			"UPDATE NewsFeed SET title=?, publication_date=?, brief=?, content=? "
			+ "WHERE news_ID=?"),
			SELECT_NEWS_ID(
			"SELECT news_ID FROM NewsFeed WHERE title=? and publication_date=?"),
			DELETE_LIST(
			"DELETE FROM NewsFeed WHERE NEWS_ID IN "),
			DELETE(
			"DELETE FROM NewsFeed WHERE NEWS_ID = ? "),
			GET_NEWS_LIST("getNewsList"),
			DELETE_NEWS("deleteNews"),
			FIND_NEWS_ID("findNewsId"),
			SQL_GET_NEXT ("select NEWSFEED_SEQ.NEXTVAL from dual");
			
	private String query;

	DBQueryEnum(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

}
