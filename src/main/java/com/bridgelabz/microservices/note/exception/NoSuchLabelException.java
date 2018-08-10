package com.bridgelabz.microservices.note.exception;

public class NoSuchLabelException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchLabelException(final String message) {
		super(message);
	}
}
