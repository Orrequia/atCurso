package com.fot.atCurso.service.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionDAO;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class QuestionServiceImpl extends AbstractServiceImpl<Question, QuestionDAO> implements QuestionService {
	
	@Autowired
	QuestionDAO questionDAO;
}
