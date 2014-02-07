package com.epam.hibernate.exeption;

/**
 * BusinessLogicException: signals about incorrect logical work in business logic.
 * 
 * @author		Sheiko Aliaksandr
 * @version		1.0 8 April 2013
 */
public final class BusinessLogicException extends Exception {

	/**Defines uid class version*/
	private static final long serialVersionUID = 1L;
	
	/**
	 *  
	 * @param message is describing incorrect work.
	 */
	public BusinessLogicException(String message) { 
		super(message);
	}
	
	/**
	 * 
	 * @param message is describing incorrect work.
	 * @param exception inserting exception.
	 */
	public BusinessLogicException(String message, Throwable exception) { 
		super(message, exception);
	}
	
	/**
	 * 
	 * @param exception inserting exception.
	 */
	public BusinessLogicException(Throwable exception) { 
		super(exception);
	}
}
