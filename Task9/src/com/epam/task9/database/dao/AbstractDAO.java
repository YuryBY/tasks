package com.epam.task9.database.dao;

import java.util.Calendar;
import java.util.List;

import com.epam.task9.database.pool.ConnectionPool;
import com.epam.task9.exeption.TechnicalException;
import com.epam.task9.model.News;

public abstract class AbstractDAO implements INewsDAO {

	/**
	 * Link to object ConnectionPool which is a storage for a database
	 * connections.
	 */
	protected ConnectionPool connectionPool;

	/**
	 * Initializes a class field - initializes a class field.
	 * 
	 * @param connectionPool
	 *            is ConnectionPool type object.
	 */
	protected AbstractDAO(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}

	/**
	 * Returns all objects certain type from database.
	 * 
	 * @return list of established for class type objects.
	 * @throws TechnicalException
	 *             if connection is null.
	 */
	public abstract List<News> getList() throws TechnicalException;

	/**
	 * Returns established for class type of object by it's id.
	 * 
	 * @param id
	 *            is id of sicking note.
	 * @return established for class type of object.
	 * @throws TechnicalException
	 *             if connection is null.
	 * @throws DAOLogicException
	 *             if given parameter is null.
	 */
	public abstract News fetchById(int id) throws TechnicalException;

	/**
	 * Deletes note with given as a parameter id.
	 * 
	 * @param id
	 *            is id of sicking note.
	 * @return true if note with id deleted or false.
	 * @throws TechnicalException
	 *             if connection is null.
	 */
	public abstract void delete(int id) throws TechnicalException;

	/**
	 * Deletes set of notes.
	 * 
	 * @param id
	 *            is id list of sicking notes.
	 * @return true if note with id deleted or false.
	 * @throws TechnicalException
	 *             if connection is null.
	 */
	public abstract void deleteList(List<Integer> idList)
			throws TechnicalException;

	/**
	 * Creates note with data from entity object.
	 * 
	 * @param entity
	 *            creates note with data from entity.
	 * @return true if note was created or else.
	 * @throws TechnicalException
	 *             if connection is null.
	 */
	public abstract int save(News entity) throws TechnicalException;

	/**
	 * @param title
	 *            is note title
	 * @param date
	 *            is note date
	 * @throws TechnicalException
	 */
	public abstract int fetchNewsId(String title, Calendar date)
			throws TechnicalException;

	public abstract void update(News news) throws TechnicalException;

}
