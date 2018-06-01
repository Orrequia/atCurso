package com.fot.atCurso.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Result;

@Repository
public interface QuizDAO extends GenericDAO<Quiz> {

	@Query(value = "SELECT FROM Quiz quiz WHERE quiz.idCourse = :idCourse ORDER BY quiz.name")
	List<Result> findByUser(@Param("idCourse") Integer idCourse, Pageable p);
}
