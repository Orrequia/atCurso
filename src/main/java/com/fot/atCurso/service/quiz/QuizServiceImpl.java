package com.fot.atCurso.service.quiz;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fot.atCurso.dao.QuizDAO;
import com.fot.atCurso.exceptions.NotFoundException;
import com.fot.atCurso.model.Course;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Tag;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.course.CourseService;
import com.fot.atCurso.service.tag.TagService;

@Service
public class QuizServiceImpl extends AbstractServiceImpl<Quiz, QuizDAO> implements QuizService {

	@Autowired
	QuizDAO quizDAO;
	
	@Autowired
	TagService tagService;
	
	@Override
	public void setTags(Quiz quiz, List<Integer> idTags) throws NotFoundException {
		List<Optional<Tag>> tags = idTags.stream().map(t -> tagService.findById(t)).collect(Collectors.toList());
		for(Optional<Tag> t : tags)
			t.orElseThrow(() -> new NotFoundException("Algunos o todos los tags no existen"));
		//tags.forEach(t -> t.orElseThrow(() -> new NotFoundException("Algunos o todos los tags no existen")));
		quiz.setTag(tags.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
	}
}
