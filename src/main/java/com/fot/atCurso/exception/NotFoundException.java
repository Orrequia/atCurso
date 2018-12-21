package com.fot.atCurso.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String MSG = "Esta entidad no existe";
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException() {
		super(MSG);
	}
}
