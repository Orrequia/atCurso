package com.fot.atCurso.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Tag;

@Repository
public interface QuestionDAO extends GenericDAO<Question>{

	List<Question> findByTag(Tag tag, Pageable p);
	
	@Query(value = "SELECT q FROM Quiz AS qz JOIn qz.question AS q WHERE qz.idQuiz = :idQuiz")
	List<Question> findByQuiz(Integer idQuiz);
}
