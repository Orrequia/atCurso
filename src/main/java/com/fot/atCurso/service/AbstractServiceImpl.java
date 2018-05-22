package com.fot.atCurso.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.AbstractDAO;

@Service
public abstract class AbstractServiceImpl<T> implements AbstractService<T, Integer>{

	@Autowired
	AbstractDAO<T> abstractDAO;
	
	public T create(T t) {
		return abstractDAO.save(t);
	}
	
	public void update(T t) {
		abstractDAO.save(t);
	}
	
	public Optional<T> findById(Integer id) {
		return abstractDAO.findById(id);
	}
	
	public Set<T> findAll(Pageable p) {
		return abstractDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}
	
	public void delete(T t) {
		abstractDAO.delete(t);
	}
}
