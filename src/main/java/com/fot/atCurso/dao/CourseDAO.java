package com.fot.atCurso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.User;

@Repository
public interface CourseDAO extends GenericDAO<Course> {

	List<Course> findByUser(User user, Pageable p);
	Optional<Course> findOneByQuiz(Quiz quiz);
}

