package com.epam.hibernate.exeption;

/**
 * LogicalException: signals about incorrect logical work in controller layer.
 * 
 * @author		Sheiko Aliaksandr
 * @version		1.0 8 April 2013
 */
public final class LogicalException extends Exception {
	
	/**Defines uid class version*/
	private static final long serialVersionUID = 1L;

	/**
	 *  
	 * @param message is describing incorrect work.
	 */
	public LogicalException(String message) { 
		super(message);
	}
	
	/**
	 * 
	 * @param message is describing incorrect work.
	 * @param exception inserting exception.
	 */
	public LogicalException(String message, Throwable exception) { 
		super(message, exception);
	}
	
	/**
	 * 
	 * @param exception inserting exception.
	 */
	public LogicalException(Throwable exception) { 
		super(exception);
	}
}
