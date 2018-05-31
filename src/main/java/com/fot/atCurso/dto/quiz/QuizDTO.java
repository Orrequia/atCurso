package com.fot.atCurso.dto.quiz;

import java.util.List;

import com.fot.atCurso.enums.ModalityEnum;

import lombok.Data;

@Data
public class QuizDTO {

	private Integer idQuiz;
	private String name;
	private ModalityEnum modality;
	private List<Integer> tags;
	private List<Integer> questions;
}
