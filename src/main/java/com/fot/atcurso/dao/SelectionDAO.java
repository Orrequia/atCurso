package com.fot.atcurso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Selection;
import com.fot.atcurso.model.User;

@Repository
public interface SelectionDAO extends GenericDAO<Selection>{

	List<Selection> findByUser(User user);
	List<Selection> findByUserAndQuizOrderByAskedDateDesc(User user, Quiz quiz);
	Optional<Selection> findOneByUserAndQuizAndQuestion(User user, Quiz quiz, String question);
}
