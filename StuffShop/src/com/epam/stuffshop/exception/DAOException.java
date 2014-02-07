package com.epam.stuffshop.exception;

/**
 * ControllerException: signals about incorrect work in DAO layer.
 * 
 * @author Yury Bakhmutski
 * @version 1.0
 * @since 2013-04-10
 */
public class DAOException extends Exception {
	/**Defines uid class version*/
	private static final long serialVersionUID = 1L;
	
	/**Defines parent for current exception*/
	private Exception parentException;

	/**
	 * @param message is describing incorrect work.
	 */
	public DAOException(String er) {
		super(er);
	}
	
	/**
	 * @param message is describing incorrect work.
	 * @param exception inserting exception.
	 */
	public DAOException(String er, Exception e) {
		super(er);
		parentException = e;
	}

	/**
	 * @param exception inserting exception.
	 */
	public DAOException(Exception e) {
		super(e);
		parentException = e;
	}

	/**
	 * @return parent for current exception
	 */
	public Exception getParentException() {
		return parentException;
	}
}
