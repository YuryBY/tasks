package com.epam.employees.database.dao;

import java.util.List;

import com.epam.employees.exeption.TechnicalException;

public interface IDAO<T> {

	public List<T> getAll() throws TechnicalException;

	public T fetchById(long id) throws TechnicalException;

	public T update(T item) throws TechnicalException;

	public boolean delete(T item) throws TechnicalException;

	public T save(T item) throws TechnicalException;

	public List<T> getDefinedQuantity(int from, int quantity)
			throws TechnicalException;
}
