package com.fot.atcurso.service.tag;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atcurso.dao.TagDAO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Tag;
import com.fot.atcurso.service.AbstractServiceImpl;

@Service
public class TagServiceImpl extends AbstractServiceImpl<Tag, TagDAO> implements TagService {

	@Autowired
	TagDAO tagDAO;
	
	@Override
	public boolean isEqual(Tag t1, Tag t2) {
		return StringUtils.equals(t1.getName(), t2.getName());
	}
	
	@Override
	public void setValues(Tag to, Tag from) {
		to.setName(from.getName());
	}
	
	@Override
	public Tag getAndCheck(Integer idTag) throws NotFoundException {
		Optional<Tag> tag = findById(idTag);
		return tag.orElseThrow(() -> new NotFoundException("El tag no existe"));
	}
}
