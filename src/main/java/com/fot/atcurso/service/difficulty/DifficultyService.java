package com.fot.atcurso.service.difficulty;

import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Difficulty;
import com.fot.atcurso.service.AbstractService;

public interface DifficultyService  extends AbstractService<Difficulty, Integer> {

	boolean isEqual(Difficulty u1, Difficulty u2);
	void setValues(Difficulty to, Difficulty from);
	Difficulty getAndCheck(Integer idDifficulty) throws NotFoundException;
}
