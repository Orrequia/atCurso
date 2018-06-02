package com.fot.atCurso.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Quiz;

@Repository
public interface QuizDAO extends GenericDAO<Quiz> {

	//@Query(value = "SELECT q FROM Quiz AS q JOIN Course AS c WHERE c.idCourse = :idCourse ORDER BY q.name")
	@Query(value = "SELECT q FROM Course AS c JOIN c.quiz AS q WHERE c.idCourse = :idCourse OrdeR BY q.name")
	List<Quiz> findByCourse(@Param("idCourse") Integer idCourse, Pageable p);
}
