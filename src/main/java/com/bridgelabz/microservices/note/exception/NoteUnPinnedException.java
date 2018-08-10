package com.bridgelabz.microservices.note.exception;

public class NoteUnPinnedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

     public NoteUnPinnedException(final String message) {
    	 super(message);
     }
}
