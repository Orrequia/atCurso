package com.fot.atCurso.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.GenericDAO;

@Service
public abstract class AbstractServiceImpl<T, D extends GenericDAO<T>> implements AbstractService<T, Integer> {

	@Autowired
	D dao;
	
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
	public Set<T> findAll(Pageable p) {
		return dao.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}
}
