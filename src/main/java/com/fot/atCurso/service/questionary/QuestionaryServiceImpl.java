package com.fot.atCurso.service.questionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionaryDAO;
import com.fot.atCurso.model.Questionary;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class QuestionaryServiceImpl extends AbstractServiceImpl<Questionary, QuestionaryDAO> implements QuestionaryService {

	@Autowired
	QuestionaryDAO questionaryDAO;
}
