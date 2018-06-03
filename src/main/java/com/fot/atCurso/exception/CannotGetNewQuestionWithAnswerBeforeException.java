package com.fot.atCurso.exception;

public class CannotGetNewQuestionWithAnswerBeforeException extends Exception {
	private static final long serialVersionUID = 856465375L;

	public static final String MSG = "Las precondiciones no están siendo cumplidas.";
	
	public CannotGetNewQuestionWithAnswerBeforeException(String message) {
		super(message);
	}
	
	public CannotGetNewQuestionWithAnswerBeforeException() {
		super(MSG);
	}
}
