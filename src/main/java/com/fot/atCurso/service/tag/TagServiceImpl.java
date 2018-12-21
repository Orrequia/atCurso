package com.fot.atCurso.service.tag;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.TagDAO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Tag;
import com.fot.atCurso.service.AbstractServiceImpl;

@Service
public class TagServiceImpl extends AbstractServiceImpl<Tag, TagDAO> implements TagService {

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
		tag.orElseThrow(() -> new NotFoundException("El tag no existe"));
		return tag.get();
	}
}
