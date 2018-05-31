package com.fot.atCurso.service.quiz;

import java.util.List;

import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.service.AbstractService;

public interface QuizService  extends AbstractService<Quiz, Integer> {
	
	public void setTags(Quiz quiz, List<Integer> idTags)  throws NotFoundException;
}

