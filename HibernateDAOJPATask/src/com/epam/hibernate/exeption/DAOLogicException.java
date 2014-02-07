package com.epam.hibernate.exeption;

/**
 * DAOLogicException: signals about incorrect logical work in dao layer.
 * 
 * @author		Sheiko Aliaksandr
 * @version		1.0 8 April 2013
 */
public final class DAOLogicException extends Exception {

	/**Defines uid class version*/
	private static final long serialVersionUID = 1L;
	
	/**
	 *  
	 * @param message is describing incorrect work.
	 */
	public DAOLogicException(String message) { 
		super(message);
	}
	
	/**
	 * 
	 * @param message is describing incorrect work.
	 * @param exception inserting exception.
	 */
	public DAOLogicException(String message, Throwable exception) { 
		super(message, exception);
	}
	
	/**
	 * 
	 * @param exception inserting exception.
	 */
	public DAOLogicException(Throwable exception) { 
		super(exception);
	}
}
