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
}
