package com.fot.atCurso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Result;

@Repository
public interface ResultDAO extends GenericDAO<Result>{
	
	@Query(value = "SELECT r FROM User AS u JOIN u.result AS r WHERE u.idUser = :idUser ORDER BY r.date desc")
	List<Result> findByUser(@Param("idUser") Integer idUser, Pageable p);
	
	@Query(value = "SELECT r "
			+ "FROM Course AS c JOIN c.user AS u JOIN c.quiz AS q JOIN u.result AS r JOIN r.quiz q2 "
			+ "WHERE c.idCourse = :idCourse AND r.idResult = :idResult AND q.idQuiz = q2.idQuiz")
	Optional<Result> findOneByCourse(@Param("idCourse") Integer idCourse, @Param("idResult") Integer idResult);
	
	@Query(value = "SELECT r "
			+ "FROM Course AS c JOIN c.user AS u JOIN c.quiz AS q JOIN u.result AS r JOIN r.quiz q2 "
			+ "WHERE c.idCourse = :idCourse AND q.idQuiz = q2.idQuiz ORDER BY r.date desc")
	List<Result> findByCourse(@Param("idCourse") Integer idCourse, Pageable p);
}
