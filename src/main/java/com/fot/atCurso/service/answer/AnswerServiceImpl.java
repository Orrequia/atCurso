package com.fot.atCurso.service.answer;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.AnswerDAO;
import com.fot.atCurso.model.Answer;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;

	@Override
	public Answer create(Answer t) {
		return answerDAO.save(t);
	}

	@Override
	public void update(Answer t) {
		answerDAO.save(t);
	}

	@Override
	public Optional<Answer> findById(Integer id) {
		return answerDAO.findById(id);
	}

	@Override
	public Set<Answer> findAll(Pageable p) {
		return answerDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(Answer t) {
		answerDAO.delete(t);
	}
}
