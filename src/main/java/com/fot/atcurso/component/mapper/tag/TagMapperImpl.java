package com.fot.atcurso.component.mapper.tag;

import org.springframework.stereotype.Component;

import com.fot.atcurso.component.mapper.AbstractMapper;
import com.fot.atcurso.dto.tag.TagDTO;
import com.fot.atcurso.model.Tag;

@Component
public class TagMapperImpl extends AbstractMapper<Tag, TagDTO> implements TagMapper {
	
	@Override
	public Class<? extends TagDTO> dtoClazz() {
		return TagDTO.class;
	}
	
	@Override
	public Class<? extends Tag> modelClazz() {
		return Tag.class;
	}
}
