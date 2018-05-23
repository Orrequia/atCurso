package com.fot.atCurso.component.mapper;

import java.util.Set;

public interface Mapper<M, D> {
	
	M dtoToModel(D dto);
	
	D modelToDto(M model);
	
	Set<M> dtoToModel(Set<D> dtos);
	
	Set<D> modelToDto(Set<M> models);
	
	Class<? extends D> dtoClazz();
	
	Class<? extends M> modelClazz();
}
