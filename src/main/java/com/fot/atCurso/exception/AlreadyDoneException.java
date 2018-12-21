package com.fot.atCurso.exception;

public class AlreadyDoneException extends Exception {
	private static final long serialVersionUID = 85646565375L;

	private static final String MSG = "Las precondiciones no están siendo cumplidas.";
	
	public AlreadyDoneException(String message) {
		super(message);
	}
	
	public AlreadyDoneException() {
		super(MSG);
	}
}
