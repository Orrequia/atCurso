package com.fot.atcurso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fot.atcurso.model.Course;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.User;

@Repository
public interface CourseDAO extends GenericDAO<Course> {

	List<Course> findByUser(User user, Pageable p);
	Optional<Course> findOneByQuiz(Quiz quiz);
}

