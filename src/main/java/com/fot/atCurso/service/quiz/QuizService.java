package com.fot.atCurso.service.quiz;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fot.atCurso.exception.ConstraintBreakException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.service.AbstractService;

public interface QuizService  extends AbstractService<Quiz, Integer> {
	
	List<Quiz> findQuizByCourse(Integer idCourse, Pageable p) throws NotFoundException;
	Quiz findOneQuizByCourse(Integer idCourse, Integer idQuiz) throws NotFoundException;
	Quiz addToCourse(Integer idCourse, Quiz quiz) throws NotFoundException, ConstraintBreakException;
	void updateToCourse(Integer idCourse, Integer idQuiz, Quiz newQuiz) throws NotFoundException, ConstraintBreakException;
	void deleteToCourse(Integer idCourse, Integer idQuiz, Quiz bodyQuiz) throws NotFoundException, UnequalObjectsException;
	
	Quiz getAndCheck(Integer idQuiz) throws NotFoundException;
	Quiz getAndCheckBelongCourse(Course course, Integer idQuiz) throws NotFoundException;
	
	boolean isEqual(Quiz q1, Quiz q2);
	void setValues(Quiz to, Quiz from);
	
	void generateQuestions(Quiz quiz, Integer nQuestions) throws ConstraintBreakException;
}

