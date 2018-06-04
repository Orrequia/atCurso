package com.fot.atCurso.exception;

public class RequirementsNotMetException extends Exception {
	private static final long serialVersionUID = 856465375L;

	public static final String MSG = "Las precondiciones no están siendo cumplidas.";
	
	public RequirementsNotMetException(String message) {
		super(message);
	}
	
	public RequirementsNotMetException() {
		super(MSG);
	}
}
