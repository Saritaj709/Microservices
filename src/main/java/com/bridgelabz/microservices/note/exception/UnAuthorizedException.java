package com.bridgelabz.microservices.note.exception;

public class UnAuthorizedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthorizedException(final String message) {
		super(message);
	}
}
