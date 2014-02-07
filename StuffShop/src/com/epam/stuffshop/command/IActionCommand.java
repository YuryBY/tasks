package com.epam.stuffshop.command;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.stuffshop.exception.ControllerException;

/**
 * IActionCommand: interface which is implemented by all existing commands.
 * 
 * @author Yury Bakhmutski
 * @version 1.0
 * @since 2013-04-10
 */

public interface IActionCommand {

	public static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	public static final Lock readLock = readWriteLock.readLock();
	public static final Lock writeLock = readWriteLock.writeLock();

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException;
}
