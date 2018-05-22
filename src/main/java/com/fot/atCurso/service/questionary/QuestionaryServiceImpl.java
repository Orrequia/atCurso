package com.fot.atCurso.service.questionary;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuestionaryDAO;
import com.fot.atCurso.model.Questionary;

@Service
public class QuestionaryServiceImpl implements QuestionaryService {

	@Autowired
	QuestionaryDAO questionaryDAO;
	
	@Override
	public Questionary create(Questionary t) {
		return questionaryDAO.save(t);
	}

	@Override
	public void update(Questionary t) {
		questionaryDAO.save(t);
	}

	@Override
	public Optional<Questionary> findById(Integer id) {
		return questionaryDAO.findById(id);
	}

	@Override
	public Set<Questionary> findAll(Pageable p) {
		return questionaryDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(Questionary t) {
		questionaryDAO.delete(t);
	}
}
