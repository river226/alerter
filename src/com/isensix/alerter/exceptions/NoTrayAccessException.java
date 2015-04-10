package com.isensix.alerter.exceptions;

public class NoTrayAccessException extends Exception {

	/**
	 * Keep Eclipse happy
	 */
	private static final long serialVersionUID = 1L;

	String error = "Program was unable to access System Tray";

	public NoTrayAccessException(){
		// No input
	}

	public NoTrayAccessException(String input){
		error += "\n" + input;
	}

	public String getException() {
		return error;
	}
}
