package com.fot.atcurso.exception;

public class IdValueCannotBeReceivedException extends Exception {

	private static final long serialVersionUID = 4L;

	public static final String MSG = "El id no se puede recibir";
	
	public IdValueCannotBeReceivedException(String message) {
		super(message);
	}
	
	public IdValueCannotBeReceivedException() {
		super(MSG);
	}
}