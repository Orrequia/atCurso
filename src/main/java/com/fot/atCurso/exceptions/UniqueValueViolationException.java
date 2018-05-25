package com.fot.atCurso.exceptions;

public class UniqueValueViolationException extends Exception {

	private static final long serialVersionUID = 3L;
	
	public static final String MSG = "Violación de unicidad del valor";
	
	public UniqueValueViolationException(String message) {
		super(message);
	}
	
	public UniqueValueViolationException() {
		super(MSG);
	}
}
