package com.davin.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static synchronized String day(String date){
		String dateParts[] = date.split("-");
		String day  = dateParts[0];
		return day;
	}
	
	public static synchronized String month(String date){
		String dateParts[] = date.split("-");
		String month  = dateParts[1];
		return month;
	}
	
	public static synchronized String year(String date){
		String dateParts[] = date.split("-");
		String year  = dateParts[2];
		return year;
	}
	
	public static synchronized int dayCalendar(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DATE);
		return day;
	}
	
	public static synchronized int monthCalendar(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH)+1;
		return month;
	}
	
	public static synchronized int yearCalendar(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	
}
