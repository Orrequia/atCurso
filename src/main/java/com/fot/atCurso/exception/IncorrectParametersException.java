package com.fot.atCurso.exception;

public class IncorrectParametersException extends Exception {
	private static final long serialVersionUID = 789876787L;

	public static final String MSG = "Los valores de los parámetros no están permitidos";
	
	public IncorrectParametersException(String message) {
		super(message);
	}
	
	public IncorrectParametersException() {
		super(MSG);
	}
}
