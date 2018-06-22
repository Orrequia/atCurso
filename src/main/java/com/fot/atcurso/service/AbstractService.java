package com.fot.atcurso.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.fot.atcurso.exception.IncorrectParametersException;

public interface AbstractService<T, I extends Serializable> {

	T create(T t);
	
	void update(T t);
	
	Optional<T> findById(I id);
	
	List<T> findAll(Pageable p) throws IncorrectParametersException;
	
	void delete(T t);
}
