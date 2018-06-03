package com.fot.atCurso.service.selection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fot.atCurso.dao.SelectionDAO;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Selection;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;

public class SelectionServiceImpl extends AbstractServiceImpl<Selection, SelectionDAO> implements SelectionService {
	
	@Autowired
	SelectionDAO selectionDAO;
	
	@Override
	public boolean isFirstTime(User user, Quiz quiz) {
		return selectionDAO.findByUserAndQuizOrderByAskedDateDesc(user, quiz).size() == 0;
	}
	
	@Override
	public List<Selection> create(User user, Quiz quiz, List<Question> questions) {
		Date askedDate = new Date();
		List<Selection> selections = new ArrayList<Selection>();
		for(Question q : questions) {
			Selection selection = new Selection();
			selection.setUser(user);
			selection.setQuiz(quiz);
			selection.setQuestion(q.getName());
			selection.setAskedDate(askedDate);
			selections.add(selection);
		}
		return selections;
	}
}
