package com.fot.atCurso.component.mapper.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.component.mapper.answer.AnswerMapper;
import com.fot.atCurso.dto.question.QuestionDTO;
import com.fot.atCurso.dto.question.QuestionPostDTO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.service.difficulty.DifficultyService;
import com.fot.atCurso.service.tag.TagService;


@Component
public class QuestionMapperImpl extends AbstractMapper<Question, QuestionDTO> implements QuestionMapper {

	@Autowired
	AnswerMapper answerMapper;
	
	@Autowired
	DifficultyService difficultyService;
	
	@Autowired
	TagService tagService;
	
	@Override
	public Class<? extends QuestionDTO> dtoClazz() {
		return QuestionDTO.class;
	}

	@Override
	public Class<? extends Question> modelClazz() {
		return Question.class;
	}
	
	@Override
	public Question dtoToModel(QuestionDTO dto) throws NotFoundException {
		Question question = dozer.map(dto, modelClazz());
		question.setDifficulty(difficultyService.getAndCheck(dto.getIdDifficulty()));
		question.setTag(tagService.getAndCheck(dto.getIdTag()));
		question.setAnswer(answerMapper.dtoToModel(dto.getAnswers()));
		return question;
	}
	
	@Override
	public QuestionDTO modelToDto(Question model) {
		QuestionDTO questionDTO = dozer.map(model, dtoClazz());
		questionDTO.setAnswers(answerMapper.modelToDto(model.getAnswer()));
		return questionDTO;
	}
	
	@Override
	public Question dtoToModel(QuestionPostDTO dto) throws NotFoundException {
		Question question = dozer.map(dto, modelClazz());
		question.setAnswer(answerMapper.dtoPostToModel(dto.getAnswers()));
		return question;
	}
	
	
}
