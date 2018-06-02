package com.fot.atCurso.component.mapper;

import java.util.List;

import com.fot.atCurso.exception.NotFoundException;

public interface Mapper<M, D> {
	
	M dtoToModel(D dto) throws NotFoundException;
	
	D modelToDto(M model);
	
	List<M> dtoToModel(List<D> dtos) throws NotFoundException;
	
	List<D> modelToDto(List<M> models);
	
	Class<? extends D> dtoClazz();
	
	Class<? extends M> modelClazz();
}
