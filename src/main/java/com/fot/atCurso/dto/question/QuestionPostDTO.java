package com.fot.atCurso.dto.question;

import java.util.List;

import com.fot.atCurso.dto.answer.AnswerPostDTO;

import lombok.Data;

@Data
public class QuestionPostDTO {
	
	private Integer idQuestion;
	private String name;
	private Integer tag;
	private Integer difficulty;
	private List<AnswerPostDTO> answers;
}
