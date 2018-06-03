package com.fot.atCurso.service.answer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.AnswerDAO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Answer;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Selection;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.question.QuestionService;
import com.fot.atCurso.service.quiz.QuizService;
import com.fot.atCurso.service.result.ResultService;
import com.fot.atCurso.service.selection.SelectionService;
import com.fot.atCurso.service.user.UserService;

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
	public Answer addAnswerToSelection(Integer idUser, Integer idQuiz, Integer idQuestion, Answer answer) throws NotFoundException {
		questionService.checkConditionsUserAndQuiz(idUser, idQuiz);
		Quiz quiz = quizService.getAndCheck(idQuiz);
		User user = userService.getAndCheck(idUser);
		Question question = questionService.getAndCheckBelongQuiz(quiz, idQuestion);
		updateSelection(user, quiz, question, answer);
		return getCorrect(question);
	}
	
	private void updateSelection(User user, Quiz quiz, Question question, Answer answer) throws NotFoundException {
		if(!selectionService.allQuestionsBeenAnswered(user, quiz))
			selectionService.answerTheQuestion(user, quiz, question, answer);
		else
			calculateResult(user, quiz, selectionService.findByUserAndQuiz(user, quiz));
	}
	
	private Answer getCorrect(Question question) {
		return question.getAnswer().stream().filter(a -> a.getCorrect()).collect(Collectors.toList()).get(0);
	}
	
	private void calculateResult(User user, Quiz quiz, List<Selection> selecs) throws NotFoundException {
		Integer numQuestion = selecs.size();
		Integer corQuestion = selecs.stream().filter(s -> s.getWasCorrect()).collect(Collectors.toList()).size();
		
		resultService.create(user, quiz, (float)((corQuestion/numQuestion)*10));
	}
}
