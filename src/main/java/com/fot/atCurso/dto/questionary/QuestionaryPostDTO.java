package com.fot.atCurso.dto.questionary;

import java.util.List;

import com.fot.atCurso.model.Tag;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QuestionaryPostDTO extends QuestionaryDTO {

	private List<Integer> tag;
	private Integer course;
	private Integer nQuestion;
	private Boolean auto;
}
