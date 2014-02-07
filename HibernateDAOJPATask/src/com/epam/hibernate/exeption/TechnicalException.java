package com.epam.hibernate.exeption;

/**
 * TechnicalException: signals about incorrect technical work in controller layer.
 * 
 * @author		Sheiko Aliaksandr
 * @version		1.0 8 April 2013
 */
public final class TechnicalException extends Exception{
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