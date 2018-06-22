package com.fot.atcurso.component.mapper.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atcurso.component.mapper.AbstractMapper;
import com.fot.atcurso.component.mapper.answer.AnswerMapper;
import com.fot.atcurso.dto.question.QuestionDTO;
import com.fot.atcurso.dto.question.QuestionPostDTO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.service.difficulty.DifficultyService;
import com.fot.atcurso.service.tag.TagService;


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
		if(dto.getIdDifficulty() != null) question.setDifficulty(difficultyService.getAndCheck(dto.getIdDifficulty()));
		if(dto.getIdTag() != null) question.setTag(tagService.getAndCheck(dto.getIdTag()));
		question.setAnswer(answerMapper.dtoToModel(dto.getAnswers()));
		return question;
	}
	
	@Override
	public QuestionDTO modelToDto(Question model) {
		QuestionDTO questionDTO = dozer.map(model, dtoClazz());
		if(model.getDifficulty() != null) questionDTO.setIdDifficulty(model.getDifficulty().getIdDifficulty());
		if(model.getTag() != null) questionDTO.setIdTag(model.getTag().getIdTag());
		questionDTO.setAnswers(answerMapper.modelToDto(model.getAnswer()));
		return questionDTO;
	}
	
	@Override
	public Question dtoToModel(QuestionPostDTO dto) throws NotFoundException {
		Question question = dozer.map(dto, modelClazz());
		if(dto.getIdDifficulty() != null) question.setDifficulty(difficultyService.getAndCheck(dto.getIdDifficulty()));
		if(dto.getIdTag() != null) question.setTag(tagService.getAndCheck(dto.getIdTag()));
		question.setAnswer(answerMapper.dtoPostToModel(dto.getAnswers()));
		return question;
	}
	
	
}
