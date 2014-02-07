package com.epam.stuffshop.exception;


public class TechnicalException extends Exception {

	/**Defines uid class version*/
	private static final long serialVersionUID = 1L;

	/**
	 *  
	 * @param message is describing incorrect work.
	 */
	public TechnicalException(String message) { 
		super(message);
	}
	
	/**
	 * 
	 * @param message is describing incorrect work.
	 * @param exception inserting exception.
	 */
	public TechnicalException(String message, Throwable exception) { 
		super(message, exception);
	}
	
	/**
	 * 
	 * @param exception inserting exception.
	 */
	public TechnicalException(Throwable exception) { 
		super(exception);
	}
}
