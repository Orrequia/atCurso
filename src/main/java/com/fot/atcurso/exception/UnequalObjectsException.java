package com.fot.atcurso.exception;

public class UnequalObjectsException extends Exception {
	private static final long serialVersionUID = 5L;

	public static final String MSG = "Los objetos no coinciden";
	
	public UnequalObjectsException(String message) {
		super(message);
	}
	
	public UnequalObjectsException() {
		super(MSG);
	}
}
