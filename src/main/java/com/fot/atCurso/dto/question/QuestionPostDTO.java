package com.fot.atCurso.dto.question;

import java.util.List;

import com.fot.atCurso.dto.answer.AnswerPostDTO;

import lombok.Data;

@Data
public class QuestionPostDTO {
	
	private Integer idQuestion;
	private String name;
	private Integer idTag;
	private Integer idDifficulty;
	private List<AnswerPostDTO> answers;
}
