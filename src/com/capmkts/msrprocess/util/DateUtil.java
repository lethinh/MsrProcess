package com.capmkts.msrprocess.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.capmkts.msrprocess.dao.HolidayDAO;

public class DateUtil {
	
	public static Date addDays(Date inputDate, int noOfDays){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
		
		for (int i = 0; i < noOfDays; i++) { 
			cal.add(Calendar.DATE, 1);  
			
			while(HolidayDAO.isHoliday(cal.getTime())){
				cal.add(Calendar.DATE, 1);  
			}

		}
		
		return cal.getTime();
		
	}
	
	public static Date getStartDateOfMonth(Date targetDate){
		
		Calendar calStart = Calendar.getInstance();
		
		calStart.setTime(targetDate);
		calStart.set(Calendar.DAY_OF_MONTH, 1);
		
		return calStart.getTime();
	}

	public static Date getEndDateOfMonth(Date targetDate){
		
		Calendar calEnd = Calendar.getInstance();
		
		calEnd.setTime(targetDate);
		int lastDate = calEnd.getActualMaximum(Calendar.DATE);
		
		calEnd.set(Calendar.DAY_OF_MONTH, lastDate);
		
		return calEnd.getTime();
		
	}
	
	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal;
        Calendar endCal;
        startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int workDays = 0;

        //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }

        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            ++workDays;
//            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
//                ++workDays;
//            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

        return workDays;
    }
	
	public static String formatPDFDate (String inDate) throws ParseException{
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse(inDate);
		String formattedDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
		
		return formattedDate;
	}
	
	public static String formatRateDate (String inDate) throws ParseException{
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse(inDate);
		String formattedDate = new SimpleDateFormat("MM/dd/yyyy, HH:mm").format(date);
		
		return formattedDate;
	}
	
	public static String formatPortfolioDate (String inDate) throws ParseException{
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inDate);
		String formattedDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
		
		return formattedDate;
	}
	
	public static void main(String[] arg) throws Exception{
		System.out.println(formatPDFDate("2012-05-20 22:12:00.00.0"));
	}
}
