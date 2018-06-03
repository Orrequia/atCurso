package com.fot.atCurso.service.selection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fot.atCurso.component.dates.OperationDates;
import com.fot.atCurso.dao.SelectionDAO;
import com.fot.atCurso.model.Answer;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Selection;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;

public class SelectionServiceImpl extends AbstractServiceImpl<Selection, SelectionDAO> implements SelectionService {
	
	private static final Long possibleDelay = 2L; 
	@Autowired
	SelectionDAO selectionDAO;
	
	@Autowired
	OperationDates operationDates;
	
	@Override
	public boolean isFirstTime(User user, Quiz quiz) {
		return selectionDAO.findByUserAndQuizOrderByAskedDateDesc(user, quiz).size() == 0;
	}
	
	@Override
	public List<Selection> findByUserAndQuiz(User user, Quiz quiz) {
		return selectionDAO.findByUserAndQuizOrderByAskedDateDesc(user, quiz);
	}
	
	@Override
	public Selection create(User user, Quiz quiz, Question question) {
		Date askedDate = new Date();
		Selection selection = new Selection();
		selection.setUser(user);
		selection.setQuiz(quiz);
		selection.setQuestion(question.getName());
		selection.setAskedDate(askedDate);
		return selection;
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
	
	@Override
	public void answerTheQuestion(User user, Quiz quiz, Question question, Answer answer) {
		Selection selection = selectionDAO.findOneByUserAndQuizAndQuestion(user, quiz, question.getName());
		if(selection.getRespondedDate() == null) {
			Date respondedDate = new Date();
			selection.setRespondedDate(respondedDate);
			if(operationDates.diferenceInSeconds(selection.getAskedDate(), respondedDate) <= 
					quiz.getDeliveryTime().getTime() + possibleDelay) {
				selection.setAnswer(answer.getName());
				selection.setWasCorrect(answer.getCorrect());
			}
			else {
				selection.setAnswer("");
				selection.setWasCorrect(false);
			}
		}
	}
	
	@Override
	public boolean allQuestionsBeenAnswered(User user, Quiz quiz) {
		List<Selection> selections = selectionDAO.findByUserAndQuizOrderByAskedDateDesc(user, quiz);
		return selections.size() == selections.size();
	}
}
