package com.epam.hibernate.exeption;

/**
 * DAOTechnicException: signals about incorrect technical work in dao layer.
 * 
 * @author		Sheiko Aliaksandr
 * @version		1.0 8 April 2013
 */
public final class DAOTechnicException extends Exception {

	/**Defines uid class version*/
	private static final long serialVersionUID = 1L;

	/**
	 *  
	 * @param message is describing incorrect work.
	 */
	public DAOTechnicException(String message) { 
		super(message);
	}
	
	/**
	 * 
	 * @param message is describing incorrect work.
	 * @param exception inserting exception.
	 */
	public DAOTechnicException(String message, Throwable exception) { 
		super(message, exception);
	}
	
	/**
	 * 
	 * @param exception inserting exception.
	 */
	public DAOTechnicException(Throwable exception) { 
		super(exception);
	}
}
