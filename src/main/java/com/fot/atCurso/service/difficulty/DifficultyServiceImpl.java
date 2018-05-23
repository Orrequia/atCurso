package com.fot.atCurso.service.difficulty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.DifficultyDAO;
import com.fot.atCurso.model.Difficulty;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class DifficultyServiceImpl extends AbstractServiceImpl<Difficulty, DifficultyDAO> implements DifficultyService {

	@Autowired
	DifficultyDAO difficultyDAO;
}
