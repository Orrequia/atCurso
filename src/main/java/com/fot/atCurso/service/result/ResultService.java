package com.fot.atCurso.service.result;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.exceptions.ObjectsDoNotMatchException;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractService;

public interface ResultService  extends AbstractService<Result, Integer> {

	List<Result> findResultByUser(Integer idUser, Pageable p) throws NotFoundException;
	Result findOneResultByUser(Integer idUser, Integer idResult) throws NotFoundException;
	Result addToUser(Integer idUser, Result result) throws NotFoundException;
	void updateToUser(Integer idUser, Integer idResult, Result newResult) throws NotFoundException;
	void deleteToUser(Integer idUser, Integer idResult, Result bodyResult) throws NotFoundException, ObjectsDoNotMatchException;
	Result getAndCheckBelongUser(User user, Integer idResult) throws NotFoundException;
	
	boolean isEqual(Result r1, Result r2);
	void setValues(Result to, Result from);
}
