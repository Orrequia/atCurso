package com.fot.atCurso.service.result;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractService;

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
