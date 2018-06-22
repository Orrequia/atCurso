package com.fot.atcurso.component.mapper.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atcurso.component.mapper.AbstractMapper;
import com.fot.atcurso.dto.quiz.QuizDTO;
import com.fot.atcurso.exception.NotFoundException;
import com.fot.atcurso.model.Question;
import com.fot.atcurso.model.Quiz;
import com.fot.atcurso.model.Tag;
import com.fot.atcurso.service.question.QuestionService;
import com.fot.atcurso.service.tag.TagService;

@Component
public class QuizMapperImpl extends AbstractMapper<Quiz, QuizDTO> implements QuizMapper {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	QuestionService questionService;
	
	@Override
	public Class<? extends QuizDTO> dtoClazz() {
		return QuizDTO.class;
	}

	@Override
	public Class<? extends Quiz> modelClazz() {
		return Quiz.class;
	}
	
	@Override
	public Quiz dtoToModel(QuizDTO dto) throws NotFoundException {
		List<Tag> tag = integerToTag(dto.getTags());
		List<Question> question = integerToQuestion(dto.getQuestions());
		return map(dto, tag, question);
	}
	
	@Override
	public QuizDTO modelToDto(Quiz model) {
		List<Integer> tags = tagToInteger(model.getTag());
		List<Integer> questions = questionToInteger(model.getQuestion());
		return map(model, tags, questions);
	}
	
	private List<Tag> integerToTag(List<Integer> tags) throws NotFoundException {
		if(tags != null) {
			List<Tag> tag = tags.stream().map(iT -> tagService.findById(iT))
										.filter(Optional::isPresent)
										.map(Optional::get)
										.collect(Collectors.toList());
			if(tags.size() != tag.size())
				throw new NotFoundException("Algunos o todos los tags no existen");
			return tag;
		}
		return new ArrayList<>();
	}
	
	private List<Integer> tagToInteger(List<Tag> tag) {
		return tag.stream().map(Tag::getIdTag).collect(Collectors.toList());
	}
	
	private List<Question> integerToQuestion(List<Integer> questions) throws NotFoundException {
		if(questions != null) {
			List<Question> question = questions.stream().map(iQ -> questionService.findById(iQ))
													.filter(Optional::isPresent)
													.map(Optional::get)
													.collect(Collectors.toList());
			if(question.size() != questions.size())
				throw new NotFoundException("Algunas o todas las questions no existen");
			return question;
		}
		return new ArrayList<>();
	}
	
	private List<Integer> questionToInteger(List<Question> question) {
		return question.stream().map(Question::getIdQuestion).collect(Collectors.toList());
	}
	
	private Quiz map(QuizDTO dto, List<Tag> tag, List<Question> question) {
		Quiz quiz = dozer.map(dto, modelClazz());
		quiz.setQuestion(question);
		quiz.setTag(tag);
		return quiz;
	}
	
	private QuizDTO map(Quiz model, List<Integer> tags, List<Integer> questions) {
		QuizDTO dto = dozer.map(model, dtoClazz());
		dto.setQuestions(questions);
		dto.setTags(tags);
		return dto;
	}
}
