package com.fot.atCurso.service.user;

import java.util.Optional;

import com.fot.atCurso.model.Result;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractService;

public interface UserService extends AbstractService<User, Integer> {
	
	boolean isEqual(User u1, User u2);
	
	void setValues(User to, User from);
	
	Optional<User> findByEmail(String email);
	
	public Result addResult(User user, Result result);
	
	public Optional<Result> searchResult(User user, Integer idResult);
}
