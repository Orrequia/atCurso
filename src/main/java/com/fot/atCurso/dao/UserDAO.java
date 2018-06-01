package com.fot.atCurso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Result;
import com.fot.atCurso.model.User;

@Repository
public interface UserDAO extends GenericDAO<User> {
	
	Optional<User> findOneByNameOrderByIdUserDesc(String name);	
	
	Optional<User> findOneByEmail(String email);
	
	@Query(value = "SELECT user from User user join Group group where group.idGroup = :idGroup ORDER BY u.name")
	List<User> findByCourse(@Param("idGroup") Integer idGroup, Pageable p);
}
