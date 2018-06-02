package com.fot.atCurso.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fot.atCurso.component.mapper.tag.TagMapper;
import com.fot.atCurso.dto.tag.TagDTO;
import com.fot.atCurso.exception.IdValueCannotBeReceivedException;
import com.fot.atCurso.exception.NotFoundException;
import com.fot.atCurso.exception.UnequalObjectsException;
import com.fot.atCurso.exception.ParametersNotAllowedException;
import com.fot.atCurso.model.Tag;
import com.fot.atCurso.service.tag.TagService;

@RestController
@RequestMapping(value= "/tag")
public class TagController {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	TagMapper tagMapper;
	
	@GetMapping
	public List<TagDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size) throws ParametersNotAllowedException {
		final List<Tag> tags = tagService.findAll(PageRequest.of(page, size));
		return tagMapper.modelToDto(tags);
	}
	
	@GetMapping("/{idTag}")
	public TagDTO findById(@PathVariable("idTag") Integer id) throws NotFoundException {
		final Tag tag = tagService.getAndCheck(id);
		return tagMapper.modelToDto(tag);
	}
	
	@PostMapping
	public TagDTO create(@RequestBody TagDTO dto) throws IdValueCannotBeReceivedException, ConstraintViolationException, NotFoundException {
		if(dto.getIdTag() != null) 
			throw new IdValueCannotBeReceivedException("El idTag no se puede recibir en el body");
		final Tag tag = tagMapper.dtoToModel(dto);
		final Tag createTag = tagService.create(tag);
		return tagMapper.modelToDto(createTag);
	}
	
	@PutMapping("/{idTag}")
	public void update(@PathVariable("idTag") Integer id, @RequestBody TagDTO dto) throws IdValueCannotBeReceivedException, NotFoundException {
		if(dto.getIdTag() != null) 
			throw new IdValueCannotBeReceivedException("El idTag no se puede recibir en el body");
		final Tag tag = tagService.getAndCheck(id);
		tagService.setValues(tag, tagMapper.dtoToModel(dto));
		tagService.update(tag);
	}
	
	@DeleteMapping("/{idTag}")
	public void delete(@PathVariable("idTag") Integer id, @RequestBody TagDTO dto) throws NotFoundException, UnequalObjectsException {
		final Tag tag = tagService.getAndCheck(id);
		if(!tagService.isEqual(tagMapper.dtoToModel(dto), tag)) 
			throw new UnequalObjectsException("El tag recibido no coincide con el almacenado");
		tagService.delete(tag);
	}
}
