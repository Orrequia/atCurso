package com.fot.atcurso.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fot.atcurso.model.Question;
import com.fot.atcurso.model.Tag;

@Repository
public interface QuestionDAO extends GenericDAO<Question>{

	List<Question> findByTag(Tag tag, Pageable p);
	List<Question> findByTagIn(List<Tag> tags);
}
