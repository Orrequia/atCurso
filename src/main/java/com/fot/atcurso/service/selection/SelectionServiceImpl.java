package com.fot.atcurso.service.selection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atcurso.component.dates.OperationDates;
import com.fot.atcurso.dao.SelectionDAO;
import com.fot.atcurso.exception.AlreadyDoneException;
import com.fot.atcurso.exception.ExceededTimeException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Answer;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Selection;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.AbstractServiceImpl;
import com.fot.atcurso.service.answer.AnswerService;

@Service
public class SelectionServiceImpl extends AbstractServiceImpl<Selection, SelectionDAO> implements SelectionService {
	
	private static final Long POSSIBLEDELAY = Long.valueOf(1000L); 
	
	@Autowired
	SelectionDAO selectionDAO;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	OperationDates opDates;
	
	@Override
	public boolean isFirstTime(User user, Quiz quiz) {
		return selectionDAO.findByUserAndQuizOrderByAskedDateDesc(user, quiz).isEmpty();
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
		selectionDAO.save(selection);
		return selection;
	}
	
	@Override
	public List<Selection> create(User user, Quiz quiz, List<Question> questions) {
		Date askedDate = new Date();
		List<Selection> selections = new ArrayList<>();
		for(Question q : questions) {
			Selection selection = new Selection();
			selection.setUser(user);
			selection.setQuiz(quiz);
			selection.setQuestion(q.getName());
			selection.setAskedDate(askedDate);
			selectionDAO.save(selection);
			selections.add(selection);
		}
		return selections;
	}
	
	@Override
	public void answerTheQuestion(User user, Quiz quiz, Question question, Answer answer) throws ExceededTimeException, AlreadyDoneException, NotFoundException {
		Selection selection = getAndCheck(user, quiz, question.getName());
		if(selection.getRespondedDate() == null) {
			Date resedDate = new Date();
			selection.setRespondedDate(resedDate);
			if(opDates.difference(selection.getAskedDate(), resedDate) <= quiz.getDeliveryTime().getTime() + POSSIBLEDELAY)
				completeQuestion(selection, question, answer);
			else helplessQuestion(selection);
		}
		else throw new AlreadyDoneException("Esta pregunta ya ha sido respondida");
	}
	
	@Override
	public boolean allQuestionsBeenAnswered(User user, Quiz quiz) {
		List<Selection> selections = selectionDAO.findByUserAndQuizOrderByAskedDateDesc(user, quiz);
		selections = selections.stream().filter(s -> s.getRespondedDate() != null).collect(Collectors.toList());
		return selections.size() == quiz.getQuestion().size();
	}
	
	@Override
	public void deleteByUser(User user) {
		List<Selection> selections = selectionDAO.findByUser(user);
		for(Selection s : selections) {
			selectionDAO.delete(s);
		}
	}
	
	private Selection getAndCheck(User user, Quiz quiz, String name) throws NotFoundException {
		Optional<Selection> selection = selectionDAO.findOneByUserAndQuizAndQuestion(user, quiz, name);
		return selection.orElseThrow(() -> new NotFoundException("Esta pregunta no te pertenece aún."));
	}
	
	private void completeQuestion(Selection selection, Question question, Answer answer) {
		selection.setAnswer(answer.getName());
		selection.setWasCorrect(answerService.getCorrect(question).getName().equals(answer.getName()));
		selectionDAO.save(selection);
	}
	
	private void helplessQuestion(Selection selection) throws ExceededTimeException {
		selection.setAnswer("");
		selection.setWasCorrect(false);
		selectionDAO.save(selection);
		throw new ExceededTimeException("Has respondido demasiado tarde, esta pregunta queda anulada");
	}
}
