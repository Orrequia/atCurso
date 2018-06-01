package com.fot.atCurso.component.mapper.difficulty;

import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.dto.difficulty.DifficultyDTO;
import com.fot.atCurso.model.Difficulty;

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
