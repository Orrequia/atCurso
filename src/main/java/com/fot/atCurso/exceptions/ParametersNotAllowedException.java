package com.fot.atCurso.exceptions;

public class ParametersNotAllowedException extends Exception {
	private static final long serialVersionUID = 789876787L;

	public static final String MSG = "El id no se puede recibir";
	
	public ParametersNotAllowedException(String message) {
		super(message);
	}
	
	public ParametersNotAllowedException() {
		super(MSG);
	}
}
