package com.fot.atCurso.dto.answer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AnswerPostDTO extends AnswerDTO {

	private Boolean correct;
}
