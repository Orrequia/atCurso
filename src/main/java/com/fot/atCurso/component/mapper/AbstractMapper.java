package com.fot.atCurso.component.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atCurso.exceptions.NotFoundException;

@Component
public abstract class AbstractMapper<M, D> implements Mapper<M, D> {

	@Autowired
	public DozerBeanMapper dozer;
	
	@Override
	public M dtoToModel(D dto) throws NotFoundException {
		return dozer.map(dto, modelClazz());
	}

	@Override
	public D modelToDto(M model) {
		return dozer.map(model, dtoClazz());
	}

	@Override
	public List<M> dtoToModel(List<D> dtos) throws NotFoundException {
		List<M> models = new ArrayList<M>();
		for(D dto : dtos)
			models.add(dtoToModel(dto));
		return models;
	}

	@Override
	public List<D> modelToDto(List<M> models) {
		return models.stream().map(m -> modelToDto(m)).collect(Collectors.toList());
	}
}
