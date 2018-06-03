package com.fot.atCurso.component.dates;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class OperationDatesImpl implements OperationDates {

	@Override
	public boolean compare(Date d1, Date d2) {
		if(d1 == null && d2 == null) return true;
		if(d1 == null) return false;
		if(d2 == null) return false;
		return d1.compareTo(d2) == 0;
	}
	
	@Override
	public Long difference(Date d1, Date d2) {
		return Math.abs((d1.getTime() - d2.getTime()));
	}
}
