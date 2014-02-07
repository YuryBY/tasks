package com.epam.task9.database.dao;

import java.util.Calendar;
import java.util.List;

import com.epam.task9.exeption.TechnicalException;
import com.epam.task9.model.News;

public interface INewsDAO {

	public abstract List<News> getList() throws TechnicalException;

	public abstract News fetchById(int id) throws TechnicalException;

	public abstract void deleteList(List<Integer> idList)
			throws TechnicalException;

	public abstract void delete(int id) throws TechnicalException;

	public abstract int save(News news) throws TechnicalException;

	public abstract void update(News news) throws TechnicalException;

	public abstract int fetchNewsId(String title, Calendar date)
			throws TechnicalException;

}
