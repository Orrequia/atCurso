package com.fot.atCurso.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.ResultDAO;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDAO resultDAO;
}
