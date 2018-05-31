package com.fot.atCurso.service.result;

import com.fot.atCurso.model.Result;
import com.fot.atCurso.service.AbstractService;

public interface ResultService  extends AbstractService<Result, Integer> {

	void setValues(Result to, Result from);
}
