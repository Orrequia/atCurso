package com.fot.atCurso.component.mapper.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.dto.quiz.QuizDTO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.model.Question;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Tag;
import com.fot.atCurso.service.question.QuestionService;
import com.fot.atCurso.service.tag.TagService;

@Component
public class QuizMapperImpl extends AbstractMapper<Quiz, QuizDTO> implements QuizMapper {

	@Autowired
	public DozerBeanMapper dozer;
	
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
			List<Optional<Tag>> tag = tags.stream().map(iT -> tagService.findById(iT)).collect(Collectors.toList());
			for(Optional<Tag> t: tag) 
				t.orElseThrow(() -> new NotFoundException("Algunos o todos los tags no existen"));
			return tag.stream().map(Optional::get).collect(Collectors.toList());
		}
		return new ArrayList<Tag>();
	}
	
	private List<Integer> tagToInteger(List<Tag> tag) {
		return tag.stream().map(m -> m.getIdTag()).collect(Collectors.toList());
	}
	
	private List<Question> integerToQuestion(List<Integer> questions) throws NotFoundException {
		if(questions != null) {
			List<Optional<Question>> question = questions.stream().map(iQ -> questionService.findById(iQ)).collect(Collectors.toList());
			for(Optional<Question> q: question)
				q.orElseThrow(() -> new NotFoundException("Algunas o todas las questions no existen"));
			return question.stream().map(Optional::get).collect(Collectors.toList());
		}
		return new ArrayList<Question>();
	}
	
	private List<Integer> questionToInteger(List<Question> question) {
		return question.stream().map(m -> m.getIdQuestion()).collect(Collectors.toList());
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
