package com.fot.atcurso.service.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Course;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.AbstractService;

public interface CourseService  extends AbstractService<Course, Integer> {

	List<Course> findByUser(Integer idUser, Pageable p) throws NotFoundException;
	Optional<Course> findByQuiz(Integer idQuiz) throws NotFoundException;
	
	boolean isEqual(Course u1, Course u2);
	void setValues(Course to, Course from);	
	void addQuiz(Course course, Quiz quiz);
	void removeQuiz(Course course, Quiz quiz);
	Optional<User> searchUser(Course course, Integer idUser);
	Optional<Quiz> searchQuiz(Course course, Integer idQuiz);	
	
	Course getAndCheck(Integer idCourse) throws NotFoundException;
}
