package com.fot.atCurso.service.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.AnswerDAO;
import com.fot.atCurso.model.Answer;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class AnswerServiceImpl extends AbstractServiceImpl<Answer, AnswerDAO> implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;
}
