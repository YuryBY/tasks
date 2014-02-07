package com.epam.hibernate.exeption;

/**
 * BusinessTechnicException: signals about incorrect technical work in business logic.
 * 
 * @author		Sheiko Aliaksandr
 * @version		1.0 8 April 2013
 */
public final class BusinessTechnicException extends Exception {

	/**Defines uid class version*/
	private static final long serialVersionUID = 1L;
	
	/**
	 *  
	 * @param message is describing incorrect work.
	 */
	public BusinessTechnicException(String message) { 
		super(message);
	}
	
	/**
	 * 
	 * @param message is describing incorrect work.
	 * @param exception inserting exception.
	 */
	public BusinessTechnicException(String message, Throwable exception) { 
		super(message, exception);
	}
	
	/**
	 * 
	 * @param exception inserting exception.
	 */
	public BusinessTechnicException(Throwable exception) { 
		super(exception);
	}
}
