package com.fot.atCurso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Selection;
import com.fot.atCurso.model.User;

@Repository
public interface SelectionDAO extends GenericDAO<Selection>{

	List<Selection> findByUserAndQuizOrderByAskedDateDesc(User user, Quiz quiz);
	Optional<Selection> findOneByUserAndQuizAndQuestion(User user, Quiz quiz, String question);
}
