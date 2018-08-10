package com.bridgelabz.microservices.note.exception;

public class LabelNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   public LabelNotFoundException(final String message) {
	   super(message);
   }
}
