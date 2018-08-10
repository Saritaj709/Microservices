package com.bridgelabz.microservices.note.exception;

public class LabelCreationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public LabelCreationException(final String message) {
    	super(message);
    }
}
