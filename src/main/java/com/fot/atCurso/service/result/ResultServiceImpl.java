package com.fot.atCurso.service.result;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.ResultDAO;
import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ObjectsDoNotMatchException;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.user.UserService;

@Service
public class ResultServiceImpl extends AbstractServiceImpl<Result, ResultDAO> implements ResultService {

	@Autowired
	ResultDAO resultDAO;
	
	@Autowired
	UserService userService;
	
	@Override 
	public boolean isEqual(Result r1, Result r2) {
		return r1.getDate().equals(r2.getDate()) &&
				r1.getScore().equals(r2.getScore()) &&
				r2.getQuiz().equals(r2.getQuiz());
	}
	@Override
	public void setValues(Result to, Result from) {
		to.setDate(from.getDate());
		to.setScore(from.getScore());
		to.setQuiz(from.getQuiz());
	}
	
	@Override
	public List<Result> findResultByUser(Integer idUser, Pageable p) throws NotFoundException {
		final Optional<User> user = userService.findById(idUser);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		return resultDAO.findByUser(idUser, PageRequest.of(p.getPageNumber(), p.getPageSize()));
	}
	
	@Override
	public Result findOneResultByUser(Integer idUser, Integer idResult) throws NotFoundException {
		final Optional<User> user = userService.findById(idUser);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		final Optional<Result> result = user.get().getResult().stream().filter(r -> r.getIdResult() == idResult).findFirst();
		result.orElseThrow(() -> new NotFoundException("Este resultado no existe para este usuario"));
		return result.get();
	}
	
	@Override
	public Result addToUser(Result result, Integer idUser) throws NotFoundException {
		final Optional<User> user = userService.findById(idUser);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		final Result createResult = create(result);
		userService.addResult(user.get(), createResult);
		return createResult;
	}
	
	@Override
	public void updateToUser(Integer idUser, Integer idResult, Result newResult) throws NotFoundException {
		final Optional<User> user = userService.findById(idUser);
		user.orElseThrow(() -> new NotFoundException("El usuario no existe"));
		final Optional<Result> result = user.get().getResult().stream().filter(r -> r.getIdResult() == idResult).findFirst();
		result.orElseThrow(() -> new NotFoundException("Este resultado no existe para este usuario"));
		setValues(result.get(), newResult);
		update(result.get());
	}
	
	@Override
	public void deleteToUser(Integer idUser, Integer idResult, Result bodyResult) throws NotFoundException, ObjectsDoNotMatchException {
		final User user = userService.getAndCheck(idUser);
		final Result result = getAndCheck(user, idResult);
		if(!isEqual(bodyResult, result))
			throw new ObjectsDoNotMatchException("El resultado recibido no coincide con el almacenado");
		userService.removeResult(user, result);
		delete(result);
	}
	
	@Override
	public Result getAndCheck(User user, Integer idResult) throws NotFoundException {
		final Optional<Result> result = user.getResult().stream().filter(r -> r.getIdResult() == idResult).findFirst();
		result.orElseThrow(() -> new NotFoundException("Este resultado no existe para este usuario"));
		return result.get();
	}
}
