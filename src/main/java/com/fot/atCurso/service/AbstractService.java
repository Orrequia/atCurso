package com.fot.atCurso.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.exception.ParametersNotAllowedException;

public interface AbstractService<T, ID extends Serializable> {

	T create(T t) throws ConstraintBreakException;
	
	void update(T t);
	
	Optional<T> findById(ID id);
	
	List<T> findAll(Pageable p) throws ParametersNotAllowedException;
	
	void delete(T t);
}
