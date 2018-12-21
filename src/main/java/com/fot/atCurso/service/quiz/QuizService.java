package com.fot.atCurso.service.quiz;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.service.AbstractService;

public interface QuizService  extends AbstractService<Quiz, Integer> {
	
	List<Quiz> findQuizByCourse(Integer idCourse, Pageable p) throws NotFoundException;
	Quiz findOneQuizByCourse(Integer idCourse, Integer idQuiz) throws NotFoundException;
	Quiz addToCourse(Integer idCourse, Quiz quiz) throws NotFoundException, ConstraintBreakException;
	void updateToCourse(Integer idCourse, Integer idQuiz, Quiz newQuiz) throws NotFoundException, ConstraintBreakException;
	void deleteToCourse(Integer idCourse, Integer idQuiz, Quiz bodyQuiz) throws NotFoundException, UnequalObjectsException;
	
	Quiz getAndCheck(Integer idQuiz) throws NotFoundException;
	
	void generateQuestions(Quiz quiz, Integer nQuestions) throws ConstraintBreakException;
}

