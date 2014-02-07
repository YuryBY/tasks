package com.epam.task9.database.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.task9.database.constants.DBQueryEnum;
import com.epam.task9.database.dao.utils.QueryResultsManager;
import com.epam.task9.database.pool.ConnectionPool;
import com.epam.task9.database.wrapperconnection.WrapperConnection;
import com.epam.task9.exeption.TechnicalException;
import com.epam.task9.model.News;

public final class NewsDAO extends AbstractDAO {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger.getLogger(NewsDAO.class);

	public NewsDAO(ConnectionPool connectionPool) {
		super(connectionPool);
	}

	@Override
	public List<News> getList() throws TechnicalException {
		WrapperConnection connection = connectionPool.getConnection();
		if (connection == null) {
			throw new TechnicalException("null connection!");
		}

		LinkedList<News> newsList = new LinkedList<News>();
		Statement st = null;
		try {
			st = connection.getStatement();
			ResultSet resultSet = st.executeQuery(DBQueryEnum.SELECT_ALL_NEWS
					.getQuery());

			while (resultSet.next()) {
				News news = new News();
				news.setId(QueryResultsManager.getNewsId(resultSet));
				news.setTitle(QueryResultsManager.getNewsTitle(resultSet));

				// set news date
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(QueryResultsManager.getNewsDate(resultSet));
				news.setDate(calendar);

				news.setBrief(QueryResultsManager.getBriefNews(resultSet));
				news.setContent(QueryResultsManager.getNewsContent(resultSet));
				newsList.add(news);
			}

			return newsList;
		} catch (SQLException e) {
			LOG.error(e);
			throw new TechnicalException(e);
		} finally {
			connection.closeStatement(st);
			connectionPool.releaseConnection(connection);
		}
	}

	@Override
	public News fetchById(int id) throws TechnicalException {
		WrapperConnection connection = connectionPool.getConnection();
		if (connection == null) {
			throw new TechnicalException("null connection");
		}
		PreparedStatement st = null;
		try {
			st = connection.getPreparedStatement(DBQueryEnum.SELECT_NEWS_BY_ID
					.getQuery());
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			if (resultSet.next()) {
				News news = new News();
				news.setId(id);
				news.setTitle(QueryResultsManager.getNewsTitle(resultSet));

				// set news date
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(QueryResultsManager.getNewsDate(resultSet));
				news.setDate(calendar);

				news.setBrief(QueryResultsManager.getBriefNews(resultSet));
				news.setContent(QueryResultsManager.getNewsContent(resultSet));

				return news;
			} else {
				throw new TechnicalException("null news Id");
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new TechnicalException(e);
		} finally {
			connection.closeStatement(st);
			connectionPool.releaseConnection(connection);
		}
	}

	@Override
	public void delete(int id) throws TechnicalException {
		WrapperConnection connection = connectionPool.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.getPreparedStatement(DBQueryEnum.DELETE
					.getQuery());
			statement.setInt(1, id);
			statement.executeQuery();
		} catch (SQLException e) {
			LOG.error(e);
		} finally {
			connection.closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
	}

	@Override
	public void deleteList(List<Integer> idList) throws TechnicalException {
		WrapperConnection connection = connectionPool.getConnection();
		Statement st = null;

		if (connection == null) {
			throw new TechnicalException("null connection");

		}

		if (idList == null) {
			throw new TechnicalException("null idList");
		}

		try {
			StringBuilder idString = new StringBuilder();
			idString.append("(");
			for (int id : idList) {
				idString.append(id + ",");
			}
			idString.deleteCharAt(idString.length() - 1);
			idString.append(")");

			st = connection.getStatement();
			st.executeQuery(DBQueryEnum.DELETE_LIST.getQuery() + idString);
		} catch (SQLException e) {
			LOG.error(e);
		} finally {
			connection.closeStatement(st);
			connectionPool.releaseConnection(connection);
		}

	}

	@Override
	public int save(News news) throws TechnicalException {

		int newsId = getNextId();
		WrapperConnection connection = connectionPool.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.getPreparedStatement(DBQueryEnum.CREATE_NEWS
					.getQuery());
			statement.setInt(1, newsId);
			statement.setString(2, news.getTitle());
			statement.setDate(3, new Date(news.getDate().getTimeInMillis()));
			statement.setString(4, news.getBrief());
			statement.setString(5, news.getContent());
			statement.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		} finally {
			connection.closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}

		return newsId;
	}

	@Override
	public void update(News news) throws TechnicalException {
		WrapperConnection connection = connectionPool.getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection
					.getPreparedStatement(DBQueryEnum.UPDATE_NEWS_BY_ID
							.getQuery());
			statement.setString(1, news.getTitle());
			statement.setDate(2, new Date(news.getDate().getTimeInMillis()));
			statement.setString(3, news.getBrief());
			statement.setString(4, news.getContent());
			statement.setInt(5, news.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error(e);
		} finally {
			connection.closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}

	}

	@Override
	public int fetchNewsId(String title, Calendar date)
			throws TechnicalException {
		WrapperConnection connection = connectionPool.getConnection();
		if (connection == null) {
			throw new TechnicalException("null connection!");
		}
		int id = 0;
		PreparedStatement st = null;

		try {
			st = connection.getPreparedStatement(DBQueryEnum.SELECT_NEWS_ID
					.getQuery());
			st.setString(1, title);
			st.setDate(2, new Date(date.getTime().getTime()));
			ResultSet resultSet = st.executeQuery();
			if (resultSet.next()) {
				id = QueryResultsManager.getNewsId(resultSet);
				return id;
			} else {
				throw new TechnicalException("null news Id");
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new TechnicalException(e);
		} finally {
			connection.closeStatement(st);
			connectionPool.releaseConnection(connection);
		}

	}

	// Get next id from sequence
	private int getNextId() throws TechnicalException {
		int id = 0;
		WrapperConnection connection = null;
		Statement st = null;
		try {
			connection = connectionPool.getConnection();
			st = connection.getStatement();
			ResultSet resultSet = st.executeQuery(DBQueryEnum.SQL_GET_NEXT
					.getQuery());
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new TechnicalException(e);
		} finally {
			connection.closeStatement(st);
			connectionPool.releaseConnection(connection);
		}
		return id;
	}

}
