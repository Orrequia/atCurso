package com.fot.atCurso.service.result;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fot.atCurso.component.dates.OperationDates;
import com.fot.atCurso.dao.ResultDAO;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.model.Quiz;
import com.fot.atCurso.model.Result;
import com.fot.atCurso.model.User;
import com.fot.atCurso.service.AbstractServiceImpl;
import com.fot.atCurso.service.course.CourseService;
import com.fot.atCurso.service.user.UserService;

@Service
public class ResultServiceImpl extends AbstractServiceImpl<Result, ResultDAO> implements ResultService {

	@Autowired
	ResultDAO resultDAO;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	OperationDates operationDates;
	
	@Override 
	public boolean isEqual(Result r1, Result r2) {
		return operationDates.compare(r1.getDate(), r2.getDate()) &&
				r1.getScore().equals(r2.getScore()) &&
				r1.getQuiz().equals(r2.getQuiz());
	}
	@Override
	public void setValues(Result to, Result from) {
		to.setDate(from.getDate());
		to.setScore(from.getScore());
		to.setQuiz(from.getQuiz());
	}
	
	@Override
	public Result create(User user, Quiz quiz, Float score) throws NotFoundException {
		Result result = new Result();
		result.setQuiz(quiz);
		result.setScore(score);
		result.setDate(new Date());
		addToUser(user.getIdUser(), result);
		return result;
	}
	
	@Override
	public List<Result> findResultByUser(Integer idUser, Pageable p) throws NotFoundException {
		userService.getAndCheck(idUser);
		return resultDAO.findByUser(idUser, PageRequest.of(p.getPageNumber(), p.getPageSize()));
	}
	
	@Override
	public Result findOneResultByUser(Integer idUser, Integer idResult) throws NotFoundException {
		final User user = userService.getAndCheck(idUser);
		final Result result = getAndCheckBelongUser(user, idResult);
		return result;
	}
	
	@Override
	public List<Result> findResultByCourse(Integer idCourse, Pageable p) throws NotFoundException {
		courseService.getAndCheck(idCourse);
		return resultDAO.findByCourse(idCourse, PageRequest.of(p.getPageNumber(), p.getPageSize()));
	}
	
	@Override
	public Result findOneResultByCourse(Integer idCourse, Integer idResult) throws NotFoundException {
		Optional<Result> result = resultDAO.findOneByCourse(idCourse, idResult);
		result.orElseThrow(() -> new NotFoundException("No existe este resultado para este curso"));
		return result.get();
	}
	
	@Override
	public Result addToUser(Integer idUser, Result result) throws NotFoundException {
		final User user = userService.getAndCheck(idUser);
		final Result createResult = create(result);
		userService.addResult(user, createResult);
		return createResult;
	}
	
	@Override
	public void updateToUser(Integer idUser, Integer idResult, Result newResult) throws NotFoundException {
		final User user = userService.getAndCheck(idUser);
		final Result result = getAndCheckBelongUser(user, idResult);
		setValues(result, newResult);
		update(result);
	}
	
	@Override
	public void deleteToUser(Integer idUser, Integer idResult, Result bodyResult) throws NotFoundException, UnequalObjectsException {
		final User user = userService.getAndCheck(idUser);
		final Result result = getAndCheckBelongUser(user, idResult);
		if(!isEqual(bodyResult, result))
			throw new UnequalObjectsException("El resultado recibido no coincide con el almacenado");
		userService.removeResult(user, result);
	}
	
	@Override
	public Result getAndCheckBelongUser(User user, Integer idResult) throws NotFoundException {
		final Optional<Result> result = user.getResult().stream().filter(r -> r.getIdResult() == idResult).findFirst();
		result.orElseThrow(() -> new NotFoundException("Este resultado no existe para este usuario"));
		return result.get();
	}
}
