package com.fot.atCurso.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.GenericDAO;
import com.fot.atCurso.exception.IncorrectParametersException;

@Service
public abstract class AbstractServiceImpl<T, D extends GenericDAO<T>> implements AbstractService<T, Integer> {

	private static final Integer maxSize = 10;

	@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "SpringJavaInjectionPointsAutowiringInspection"})
	@Autowired
	private D dao;


	@Override
	public T create(T t) {
		return dao.save(t);
	}

	@Override
	public void update(T t) {
		dao.save(t);
	}

	@Override
	public Optional<T> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<T> findAll(Pageable p) throws IncorrectParametersException {
		if(p.getPageNumber() < 0 || p.getPageSize() <= 0 || p.getPageSize() > maxSize)
			throw new IncorrectParametersException("Los parámetros introducidos contienen valores no permitidos, page mayor o igual a 0 y size entre 1 y " + maxSize + " incluidos");
		return dao.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toList());
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}
}
