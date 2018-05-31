package com.fot.atCurso.component.mapper;

import java.util.Set;

import com.fot.atCurso.exceptions.NotFoundException;

public interface Mapper<M, D> {
	
	M dtoToModel(D dto) throws NotFoundException;
	
	D modelToDto(M model);
	
	Set<M> dtoToModel(Set<D> dtos) throws NotFoundException;
	
	Set<D> modelToDto(Set<M> models);
	
	Class<? extends D> dtoClazz();
	
	Class<? extends M> modelClazz();
}
