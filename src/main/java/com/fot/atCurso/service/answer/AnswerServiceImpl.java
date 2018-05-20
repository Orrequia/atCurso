package com.fot.atCurso.service.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.AnswerDAO;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;
}
