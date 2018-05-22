package com.fot.atCurso.service.question;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionDAO;
import com.fot.atCurso.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionDAO questionDAO;
	
	@Override
	public Question create(Question t) {
		return questionDAO.save(t);
	}

	@Override
	public void update(Question t) {
		questionDAO.save(t);
	}

	@Override
	public Optional<Question> findById(Integer id) {
		return questionDAO.findById(id);
	}

	@Override
	public Set<Question> findAll(Pageable p) {
		return questionDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(Question t) {
		questionDAO.delete(t);
	}
}
