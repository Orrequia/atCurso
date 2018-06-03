package com.fot.atCurso.component.dates;

import java.util.Date;

public interface OperationDates {

	boolean compare(Date d1, Date d2);
	Long diferenceInSeconds(Date d1, Date d2);
}