package com.fot.atCurso.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fot.atCurso.dto.ApiErrorDTO;
import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.exception.ExceededTimeException;
import com.fot.atCurso.exception.IdValueCannotBeReceivedException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.exception.RequirementsNotMetException;
import com.fot.atCurso.exception.AlreadyDoneException;
import com.fot.atCurso.exception.IncorrectParametersException;

@ControllerAdvice(basePackages = { "com.fot.atCurso.controller"})
public class ExceptionController {
	
	@ResponseBody
	@ExceptionHandler(AlreadyDoneException.class)
	@ResponseStatus(HttpStatus.OK)
	public ApiErrorDTO error(AlreadyDoneException e) {
		return new ApiErrorDTO(200, e.getMessage());
	}
	
	
	@ResponseBody
	@ExceptionHandler(ExceededTimeException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ApiErrorDTO error(ExceededTimeException e) {
		return new ApiErrorDTO(412, e.getMessage());
	}
	
	
	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO error(NotFoundException e) {
		return new ApiErrorDTO(404, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(ConstraintViolationException e) {
		return new ApiErrorDTO(400, e.getSQLException().getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(RequirementsNotMetException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ApiErrorDTO error(RequirementsNotMetException e) {
		return new ApiErrorDTO(412, e.getMessage());
	}
	
	
	@ResponseBody
	@ExceptionHandler(ConstraintBreakException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(ConstraintBreakException e) {
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(IdValueCannotBeReceivedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(IdValueCannotBeReceivedException e) {
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(UnequalObjectsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(UnequalObjectsException e) {
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(IncorrectParametersException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(IncorrectParametersException e) {
		return new ApiErrorDTO(400, e.getMessage());
	}
}
