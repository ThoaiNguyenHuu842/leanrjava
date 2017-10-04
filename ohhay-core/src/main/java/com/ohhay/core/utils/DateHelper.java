package com.ohhay.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * @author ThoaiNH
 * create 02/03/2015
 * date helper
 */
public class DateHelper {
	private static Logger log = Logger.getLogger(DateHelper.class);

	public static String formatDateShort(java.util.Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

	public static String formatDateLong(java.util.Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(date);
	}

	public static Date convertStringToDate(String sdate) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;

	}
	public static Date convertStringToDate(String sdate, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = dateFormat.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;

	}
	// convert date to time line string
	public static String convertDateToTimeLine(java.util.Date date) {
		// second from this date to now
		long timeGo = (System.currentTimeMillis() - date.getTime()) / 1000;
		log.info(date);
		log.info(timeGo);
		// recently
		if (timeGo < 30)
			return MVCResourceBundleUtil.getResourceBundle("bookmark.recently");
		// second
		else if (timeGo < 60)
			return timeGo + " "
					+ MVCResourceBundleUtil.getResourceBundle("bookmark.lasts");
		// minute
		else if (timeGo < 3600)
			return timeGo / 60 + " "
					+ MVCResourceBundleUtil.getResourceBundle("bookmark.lastm");
		// hours
		else if (timeGo < (3600 * 24))
			return timeGo / 3600 + " "
					+ MVCResourceBundleUtil.getResourceBundle("bookmark.lasth");
		// day
		else if (timeGo < (3600 * 24 * 30))
			return timeGo / (3600 * 24) + " "
					+ MVCResourceBundleUtil.getResourceBundle("bookmark.lastd");
		// month
		else if (timeGo < (3600 * 24 * 30 * 12))
			return timeGo
					/ (3600 * 24 * 30)
					+ " "
					+ MVCResourceBundleUtil
							.getResourceBundle("bookmark.lastmo");
		// year
		else if (timeGo < (3600 * 24 * 30 * 12 * 100))
			return timeGo / (3600 * 24 * 30 * 12) + " "
					+ MVCResourceBundleUtil.getResourceBundle("bookmark.lasty");
		// so far
		else
			return MVCResourceBundleUtil.getResourceBundle("bookmark.sofar");
	}
	/**
	 * @param timeGo numberLong in second want to convert
	 * @return last time title from timeGo to current time
	 */
	public static String convertDateToTimeLine(long timeGo) {
		if (timeGo < (3600 * 24))
		{
			long h = timeGo / 3600;
			long m = (timeGo%3600) / 60;
			if(m == 0)
				return h + "h";
			else
				return h + "h" + m + "m";
		}
		// day
		else //if (timeGo < (3600 * 24 * 30))
			return timeGo / (3600 * 24) + "d";
		// month
		/*else if (timeGo < (3600 * 24 * 30 * 12))
			return timeGo / (3600 * 24 * 30)+ "mo";*/
		// year
		/*else
			return timeGo / (3600 * 24 * 30 * 12) + "y";*/
	}
	// convert string to sql.date
	public static java.sql.Date stringToSQLDate(String date, String pattern)
			throws ParseException {
		if (date == null) {
			return null;
		}
		else {
			DateFormat df = new SimpleDateFormat(pattern);
			return new java.sql.Date(df.parse(date).getTime());
		}
	}

	// convert util to sql.date
	public static java.sql.Date toSQLDate(java.util.Date date) {
		if (date == null) {
			return null;
		}
		else {
			return new java.sql.Date(date.getTime());
		}
	}

	// convert sql to util.date
	public static java.util.Date toUtilDate(java.sql.Date date) {
		if (date == null) {
			return null;
		}
		else {
			return new java.util.Date(date.getTime());
		}
	}
}
