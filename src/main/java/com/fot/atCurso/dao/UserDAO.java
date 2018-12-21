package com.fot.atCurso.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.User;

@Repository
public interface UserDAO extends GenericDAO<User> {

	@Query(value = "SELECT u FROM Course AS c JOIN c.user AS u WHERE c.idCourse = :idCourse ORDER BY u.name")
	List<User> findByCourse(@Param("idCourse") Integer idCourse, Pageable p);
}
