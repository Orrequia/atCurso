package com.fot.atCurso.service.difficulty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.DifficultyDAO;

@Service
public class DifficultyServiceImpl implements DifficultyService {

	@Autowired
	DifficultyDAO difficultyDAO;
}
