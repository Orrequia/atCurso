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
	public boolean isEqual(Quiz q1, Quiz q2) {
		return q1.getName().equals(q2.getName()) &&
				q1.getModality().equals(q2.getModality()) &&
				q1.getQuestion().equals(q2.getQuestion()) &&
				q1.getTag().equals(q2.getTag());
	}
	
	@Override
	public void setValues(Quiz to, Quiz from) {
		to.setName(from.getName());
		to.setModality(from.getModality());
		to.setQuestion(from.getQuestion());
		to.setTag(from.getTag());
	}
}
