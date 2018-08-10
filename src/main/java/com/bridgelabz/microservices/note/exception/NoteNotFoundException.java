package com.bridgelabz.microservices.note.exception;

public class NoteNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoteNotFoundException(final String message) {
		super(message);
	}
}
