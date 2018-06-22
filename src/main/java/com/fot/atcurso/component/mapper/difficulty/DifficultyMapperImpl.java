package com.fot.atcurso.component.mapper.difficulty;

import org.springframework.stereotype.Component;

import com.fot.atcurso.component.mapper.AbstractMapper;
import com.fot.atcurso.dto.difficulty.DifficultyDTO;
import com.fot.atcurso.model.Difficulty;

@Component
public class DifficultyMapperImpl extends AbstractMapper<Difficulty, DifficultyDTO> implements DifficultyMapper {
	
	@Override
	public Class<? extends DifficultyDTO> dtoClazz() {
		return DifficultyDTO.class;
	}
	
	@Override
	public Class<? extends Difficulty> modelClazz() {
		return Difficulty.class;
	}
}
