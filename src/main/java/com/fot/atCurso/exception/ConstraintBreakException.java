package com.fot.atCurso.exception;

public class ConstraintBreakException extends Exception {
	private static final long serialVersionUID = 898967L;

	public static final String MSG = "Esta entiedad no existe";
	
	public ConstraintBreakException(String message) {
		super(message);
	}
	
	public ConstraintBreakException() {
		super(MSG);
	}
}
