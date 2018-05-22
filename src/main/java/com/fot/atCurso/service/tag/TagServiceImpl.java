package com.fot.atCurso.service.tag;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.TagDAO;
import com.fot.atCurso.model.Tag;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagDAO tagDAO;
	
	@Override
	public Tag create(Tag t) {
		return tagDAO.save(t);
	}

	@Override
	public void update(Tag t) {
		tagDAO.save(t);
	}

	@Override
	public Optional<Tag> findById(Integer id) {
		return tagDAO.findById(id);
	}

	@Override
	public Set<Tag> findAll(Pageable p) {
		return tagDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(Tag t) {
		tagDAO.delete(t);
	}
}
