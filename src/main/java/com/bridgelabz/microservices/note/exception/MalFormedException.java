package com.bridgelabz.microservices.note.exception;

public class MalFormedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   public MalFormedException(final String message) {
	   super(message);
   }
}
