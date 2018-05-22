package com.fot.atCurso.service.result;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.ResultDAO;
import com.fot.atCurso.model.Result;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDAO resultDAO;
	
	@Override
	public Result create(Result t) {
		return resultDAO.save(t);
	}

	@Override
	public void update(Result t) {
		resultDAO.save(t);
	}

	@Override
	public Optional<Result> findById(Integer id) {
		return resultDAO.findById(id);
	}

	@Override
	public Set<Result> findAll(Pageable p) {
		return resultDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(Result t) {
		resultDAO.delete(t);
	}
}
