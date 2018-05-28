package com.fot.atCurso.dto.questionary;

import com.fot.atCurso.enums.ModalityEnum;

import lombok.Data;

@Data
public class QuestionaryDTO {

	private Integer idQuestionary;
	private String name;
	private ModalityEnum modality;
	private Integer course;
}
