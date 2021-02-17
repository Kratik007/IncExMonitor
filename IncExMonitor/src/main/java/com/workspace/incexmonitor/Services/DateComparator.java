package com.workspace.incexmonitor.Services;

import java.util.Comparator;

import com.workspace.incexmonitor.Entities.Income;

public class DateComparator implements Comparator<Income>{

	@Override
	public int compare(Income o1, Income o2) {
		return o2.getDate().compareTo(o1.getDate());
	}


}
