package com.fot.atCurso.service.difficulty;

import com.fot.atCurso.model.Difficulty;
import com.fot.atCurso.service.AbstractService;

public interface DifficultyService  extends AbstractService<Difficulty, Integer> {

	boolean isEqual(Difficulty u1, Difficulty u2);
	void setValues(Difficulty to, Difficulty from);
}
