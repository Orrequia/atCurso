package com.fot.atCurso.service.questionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionaryDAO;

@Service
public class QuestionaryServiceImpl implements QuestionaryService {

	@Autowired
	QuestionaryDAO questionaryDAO;
}
