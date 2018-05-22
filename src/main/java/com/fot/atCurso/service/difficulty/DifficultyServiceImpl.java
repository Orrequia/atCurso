package com.fot.atCurso.service.difficulty;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.DifficultyDAO;
import com.fot.atCurso.model.Difficulty;

@Service
public class DifficultyServiceImpl implements DifficultyService {

	@Autowired
	DifficultyDAO difficultyDAO;
	
	@Override
	public Difficulty create(Difficulty t) {
		return difficultyDAO.save(t);
	}

	@Override
	public void update(Difficulty t) {
		difficultyDAO.save(t);
	}

	@Override
	public Optional<Difficulty> findById(Integer id) {
		return difficultyDAO.findById(id);
	}

	@Override
	public Set<Difficulty> findAll(Pageable p) {
		return difficultyDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(Difficulty t) {
		difficultyDAO.delete(t);
	}
}
