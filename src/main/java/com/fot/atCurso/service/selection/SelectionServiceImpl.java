package com.fot.atCurso.service.selection;

import org.springframework.beans.factory.annotation.Autowired;

import com.fot.atCurso.dao.SelectionDAO;
import com.fot.atCurso.model.Selection;
import com.fot.atCurso.service.AbstractServiceImpl;

public class SelectionServiceImpl extends AbstractServiceImpl<Selection, SelectionDAO> implements SelectionService {
	
	@Autowired
	SelectionDAO selectionDAO;
}
