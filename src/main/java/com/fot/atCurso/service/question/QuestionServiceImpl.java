package com.fot.atCurso.service.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionDAO;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionDAO questionDAO;
}
