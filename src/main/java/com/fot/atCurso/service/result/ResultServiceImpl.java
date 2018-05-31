package com.fot.atCurso.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.ResultDAO;
import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class ResultServiceImpl extends AbstractServiceImpl<Result, ResultDAO> implements ResultService {

	@Autowired
	ResultDAO resultDAO;
	
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
}
