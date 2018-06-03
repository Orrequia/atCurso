package com.fot.atCurso.exception;

public class CompletedQuizException extends Exception {
	private static final long serialVersionUID = 85646565375L;

	public static final String MSG = "Las precondiciones no están siendo cumplidas.";
	
	public CompletedQuizException(String message) {
		super(message);
	}
	
	public CompletedQuizException() {
		super(MSG);
	}
}
