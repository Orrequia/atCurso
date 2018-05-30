package com.fot.atCurso.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.fot.atCurso.exceptions.ParametersNotAllowedException;

public interface AbstractService<T, ID extends Serializable> {

	T create(T t);
	
	void update(T t);
	
	Optional<T> findById(ID id);
	
	Set<T> findAll(Pageable p) throws ParametersNotAllowedException;
	
	void delete(T t);
}
