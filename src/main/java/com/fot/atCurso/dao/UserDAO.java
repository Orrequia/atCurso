package com.fot.atCurso.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.User;

@Repository
public interface UserDAO extends AbstractDAO<User>{
	
	Optional<User> findOneByNameOrderByIdUserDesc(String name);	
}
