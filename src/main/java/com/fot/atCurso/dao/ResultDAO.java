package com.fot.atCurso.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Result;

@Repository
public interface ResultDAO extends GenericDAO<Result>{
	
	@Query(value = "SELECT FROM Result result WHERE result.idUser = :idUser ORDER BY result.date desc")
	List<Result> findByUser(@Param("idUser") Integer idUser, Pageable p);
}
