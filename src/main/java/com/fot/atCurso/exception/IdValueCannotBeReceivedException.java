package com.fot.atCurso.exception;

public class IdValueCannotBeReceivedException extends Exception {

	private static final long serialVersionUID = 4L;

	private static final String MSG = "El id no se puede recibir";
	
	public IdValueCannotBeReceivedException(String message) {
		super(message);
	}
	
	public IdValueCannotBeReceivedException() {
		super(MSG);
	}
}