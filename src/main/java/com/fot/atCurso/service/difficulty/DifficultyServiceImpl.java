package com.fot.atCurso.service.difficulty;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.DifficultyDAO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Difficulty;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class DifficultyServiceImpl extends AbstractServiceImpl<Difficulty, DifficultyDAO> implements DifficultyService {

	@Autowired
	DifficultyDAO difficultyDAO;
	
	@Override
	public boolean isEqual(Difficulty u1, Difficulty u2) {
		return StringUtils.equals(u1.getName(), u2.getName());
	}
	
	@Override
	public void setValues(Difficulty to, Difficulty from) {
		to.setName(from.getName());
	}
	
	@Override
	public Difficulty getAndCheck(Integer idDifficulty) throws NotFoundException {
		Optional<Difficulty> difficulty = findById(idDifficulty);
		difficulty.orElseThrow(() -> new NotFoundException("La dificultad no existe"));
		return difficulty.get();
	}
}
