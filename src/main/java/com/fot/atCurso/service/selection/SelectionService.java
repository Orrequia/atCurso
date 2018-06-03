package com.fot.atCurso.service.selection;

import java.util.List;

import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Selection;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractService;

public interface SelectionService extends AbstractService<Selection, Integer> {

	boolean isFirstTime(User user, Quiz quiz);
	
	List<Selection> findByUserAndQuiz(User user, Quiz quiz);
	Selection create(User user, Quiz quiz, Question question);
	List<Selection> create(User user, Quiz quiz, List<Question> questions);
}
