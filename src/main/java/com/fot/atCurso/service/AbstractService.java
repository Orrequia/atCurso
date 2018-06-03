package com.fot.atCurso.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.fot.atCurso.exception.IncorrectParametersException;

public interface AbstractService<T, ID extends Serializable> {

	T create(T t);
	
	void update(T t);
	
	Optional<T> findById(ID id);
	
	List<T> findAll(Pageable p) throws IncorrectParametersException;
	
	void delete(T t);
}
