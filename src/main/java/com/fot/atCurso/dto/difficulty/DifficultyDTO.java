package com.fot.atCurso.dto.difficulty;

import java.io.Serializable;

import lombok.Data;

@Data
public class DifficultyDTO implements Serializable {

	private static final long serialVersionUID = 196677465L;
	
	private Integer idDifficulty;
	private String name;
}
