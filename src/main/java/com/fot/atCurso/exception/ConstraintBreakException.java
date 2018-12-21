package com.fot.atCurso.exception;

public class ConstraintBreakException extends Exception {
	private static final long serialVersionUID = 898967L;

	private static final String MSG = "Hay restricciones no cumplidas.";
	
	public ConstraintBreakException(String message) {
		super(message);
	}
	
	public ConstraintBreakException() {
		super(MSG);
	}
}
