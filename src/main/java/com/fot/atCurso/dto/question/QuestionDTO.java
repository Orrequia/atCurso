package com.fot.atCurso.dto.question;

import java.util.List;

import com.fot.atCurso.dto.answer.AnswerDTO;

import lombok.Data;

@Data
public class QuestionDTO {

	private Integer idQuestion;
	private String name;
	private Integer idTag;
	private Integer idDifficulty;
	private List<AnswerDTO> answers;
}
