package com.fot.atcurso.service.result;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.exception.UnequalObjectsException;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Result;
import com.fot.atcurso.model.User;
import com.fot.atcurso.service.AbstractService;

public interface ResultService  extends AbstractService<Result, Integer> {

	Result create(User user, Quiz quiz, Float score) throws NotFoundException;
	
	List<Result> findResultByUser(Integer idUser, Pageable p) throws NotFoundException;
	Result findOneResultByUser(Integer idUser, Integer idResult) throws NotFoundException;
	List<Result> findResultByCourse(Integer idCourse, Pageable p) throws NotFoundException;
	Result findOneResultByCourse(Integer idCourse, Integer idResult) throws NotFoundException;
	Result addToUser(Integer idUser, Result result) throws NotFoundException;
	void updateToUser(Integer idUser, Integer idResult, Result newResult) throws NotFoundException;
	void deleteToUser(Integer idUser, Integer idResult, Result bodyResult) throws NotFoundException, UnequalObjectsException;
	Result getAndCheckBelongUser(User user, Integer idResult) throws NotFoundException;
	
	boolean isEqual(Result r1, Result r2);
	void setValues(Result to, Result from);
}
