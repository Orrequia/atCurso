package com.fot.atcurso.exception;

public class ExceededTimeException extends Exception {
	private static final long serialVersionUID = 856465676675L;

	public static final String MSG = "Se ha excedido el tiempo limitado.";
	
	public ExceededTimeException(String message) {
		super(message);
	}
	
	public ExceededTimeException() {
		super(MSG);
	}
}
