package com.fot.atCurso.exceptions;

public class ObjectsDoNotMatchException extends Exception {
	private static final long serialVersionUID = 5L;

	public static final String MSG = "Los objetos no coinciden";
	
	public ObjectsDoNotMatchException(String message) {
		super(message);
	}
	
	public ObjectsDoNotMatchException() {
		super(MSG);
	}
}
