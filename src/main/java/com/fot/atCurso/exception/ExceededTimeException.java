package com.fot.atCurso.exception;

public class ExceededTimeException extends Exception {
	private static final long serialVersionUID = 856465676675L;

	private static final String MSG = "Se ha excedido el tiempo limitado.";
	
	public ExceededTimeException(String message) {
		super(message);
	}
	
	public ExceededTimeException() {
		super(MSG);
	}
}
