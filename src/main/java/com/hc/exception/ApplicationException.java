package com.hc.exception;

/**
 * Application exception base class
 * @author timmy
 *
 */
public class ApplicationException extends RuntimeException {
	/**
	 * default constructor
	 */
	public ApplicationException() {
	}

	/**
	 * Create a new ApplicationException instance
	 * @param msg exception message
	 */
	public ApplicationException(String msg) {
		super(msg);
	}
	
	/**
	 * Create a new ApplicationException instance
	 * @param msg exception message
	 * @param innerException cause
	 */
	public ApplicationException(String msg, Throwable innerException) {
		super(msg, innerException);
	}
}
