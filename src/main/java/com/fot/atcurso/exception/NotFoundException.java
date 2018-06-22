package com.fot.atcurso.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public static final String MSG = "Esta entiedad no existe";
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException() {
		super(MSG);
	}
}
