package com.fot.atCurso.service.result;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.service.AbstractService;

public interface ResultService  extends AbstractService<Result, Integer> {

	List<Result> findResultByUser(Integer idUser, Pageable p) throws NotFoundException;
	
	boolean isEqual(Result r1, Result r2);
	void setValues(Result to, Result from);
}
