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

	private final AnswerMapper answerMapper;
	private final DifficultyService difficultyService;
	private final TagService tagService;

	@Autowired
	public QuestionMapperImpl(TagService tagService, DifficultyService difficultyService, AnswerMapper answerMapper) {
		this.tagService = tagService;
		this.difficultyService = difficultyService;
		this.answerMapper = answerMapper;
	}

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
	public Question dtoToModel(QuestionPostDTO dtoPost) throws NotFoundException {
		Question question = dozer.map(dtoPost, modelClazz());
		if(dtoPost.getIdDifficulty() != null) question.setDifficulty(difficultyService.getAndCheck(dtoPost.getIdDifficulty()));
		if(dtoPost.getIdTag() != null) question.setTag(tagService.getAndCheck(dtoPost.getIdTag()));
		question.setAnswer(answerMapper.dtoPostToModel(dtoPost.getAnswers()));
		return question;
	}
}
