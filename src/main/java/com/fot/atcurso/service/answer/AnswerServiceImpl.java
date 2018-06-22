package com.fot.atcurso.service.answer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atcurso.dao.AnswerDAO;
import com.fot.atcurso.exception.AlreadyDoneException;
import com.fot.atcurso.exception.ExceededTimeException;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Answer;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Selection;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.AbstractServiceImpl;
import com.fot.atcurso.service.question.QuestionService;
import com.fot.atcurso.service.quiz.QuizService;
import com.fot.atcurso.service.result.ResultService;
import com.fot.atcurso.service.selection.SelectionService;
import com.fot.atcurso.service.user.UserService;

@Service
public class AnswerServiceImpl extends AbstractServiceImpl<Answer, AnswerDAO> implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	SelectionService selectionService;
	
	@Override
	public Answer addAnswerToSelection(Integer idUser, Integer idQuiz, Integer idQuestion, Answer answer) throws NotFoundException, ExceededTimeException, AlreadyDoneException {
		questionService.checkConditionsUserAndQuiz(idUser, idQuiz);
		Quiz quiz = quizService.getAndCheck(idQuiz);
		User user = userService.getAndCheck(idUser);
		Question question = questionService.getAndCheckBelongQuiz(quiz, idQuestion);
		updateSelection(user, quiz, question, answer);
		return getCorrect(question);
	}
	
	private void updateSelection(User user, Quiz quiz, Question question, Answer answer) throws NotFoundException, ExceededTimeException, AlreadyDoneException {
		if(!selectionService.allQuestionsBeenAnswered(user, quiz)) {
			selectionService.answerTheQuestion(user, quiz, question, answer);
			if(selectionService.allQuestionsBeenAnswered(user, quiz))
				calculateResult(user, quiz, selectionService.findByUserAndQuiz(user, quiz));
		}
		else throw new AlreadyDoneException("Ya has realizado este cuestionario");
	}
	
	@Override
	public Answer getCorrect(Question question) {
		return question.getAnswer().stream().filter(a -> a.getCorrect() != null && a.getCorrect()).collect(Collectors.toList()).get(0);
	}
	
	private void calculateResult(User user, Quiz quiz, List<Selection> selecs) throws NotFoundException {
		Integer numQuestion = selecs.size();
		Integer corQuestion = selecs.stream().filter(s -> s.getWasCorrect() != null && s.getWasCorrect()).collect(Collectors.toList()).size();
		resultService.create(user, quiz, ((float)corQuestion/numQuestion)*10);
	}
}
