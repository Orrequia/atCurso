package com.fot.atCurso.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Tag;

@Repository
public interface QuestionDAO extends GenericDAO<Question>{

	List<Question> findByTag(Tag tag, Pageable p);
	List<Question> findByTagIn(List<Tag> tags);
}
