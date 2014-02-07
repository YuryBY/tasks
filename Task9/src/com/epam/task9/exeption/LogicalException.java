package com.epam.task9.exeption;

public class LogicalException extends Exception {

	/** Defines uid class version */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            is describing incorrect work.
	 */
	public LogicalException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 *            is describing incorrect work.
	 * @param exception
	 *            inserting exception.
	 */
	public LogicalException(String message, Throwable exception) {
		super(message, exception);
	}

	/**
	 * 
	 * @param exception
	 *            inserting exception.
	 */
	public LogicalException(Throwable exception) {
		super(exception);
	}
}
