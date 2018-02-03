package com.hc.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date related utility methods
 * @author hc
 *
 */
public final class DateUtils {
	private static Log log = LogFactory.getLog(DateUtils.class);
	
	public static final Date MIN;
	
	private static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private static String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final Object syncLock = new Object();
	
	private static Calendar calendarInstance;
	
	static {
		// set minimum date to 1900/1/1
		calendarInstance = Calendar.getInstance();
		calendarInstance.set(Calendar.YEAR, 1900);
		calendarInstance.set(Calendar.MONTH, 0);
		calendarInstance.set(Calendar.DAY_OF_MONTH, 1);

		MIN = calendarInstance.getTime();
	}

	/**
	 * get default date pattern
	 */
	public static String getDefaultDatePattern() {
		return DEFAULT_DATE_PATTERN;
	}

	/**
	 * get default time pattern
	 */
	public static String getDefaultTimePattern() {
		return DEFAULT_TIME_PATTERN;
	}

	/**
	 * set default date pattern
	 */
	public static void setDefaultDatePattern(String defaultDatePattern) {
		synchronized (syncLock) {
			DEFAULT_DATE_PATTERN = defaultDatePattern;
		}
	}

	/**
	 * set default time pattern
	 */
	public static void setDefaultTimePattern(String defaultTimePattern) {
		synchronized (syncLock) {
			DEFAULT_TIME_PATTERN = defaultTimePattern;
		}
	}

    /**
     * get date part of Date instance (reset hour, minutes and seconds to 0)
     */
    public static Date dateOfDateTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
		c.set(Calendar.AM_PM, Calendar.AM);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
	
	/**
	 * get date part of today (reset hour, minutes and seconds to 0)
	 * @return
	 */
	public static Date dateOfToday() {
        return dateOfDateTime(new Date());
	}
	
	/**
	 * create a date
	 * @param year year
	 * @param month month
	 * @param day day
	 * @return
	 */
	public static Date create(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, 0, 0, 0);
		return c.getTime();
	}
	
	/**
	 * get a time with specified days difference with given time
	 * @param time time
	 * @param days days 
	 * @return
	 */
	public static Date daysDiff(Date time, int days) {
		return diff(Calendar.DAY_OF_MONTH, time, days);
	}
	
	/**
	 * get specific different time to given time
	 * @param field field
	 * @param time time
	 * @param amount amount
	 * @return
	 */
	public static Date diff(int field, Date time, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(field, amount);
		return c.getTime();
	}
	
	/**
	 * format date with default DATE pattern
	 * @param date date
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(date, getDefaultDatePattern());
	}
	
	/**
	 * format date with default TIME pattern
	 * @param date date
	 * @return
	 */
	public static String formatTime(Date date) {
		return format(date, getDefaultTimePattern());
	}
	
	/**
	 * parse date with default date pattern
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date) {
		return parse(date, getDefaultDatePattern());
	}
	
	/**
	 * parse date with default time pattern
	 * @param date
	 * @return
	 */
	public static Date parseTime(String date) {
		return parse(date, getDefaultTimePattern());
	}
	
	/**
	 * format date string 
	 * @param date date
	 * @param pattern pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if(pattern == null)
			return SimpleDateFormat.getDateTimeInstance().format(date);
		else {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.format(date);
		}
	}
	
	/**
	 * parse date
	 * @param date date string
	 * @param pattern date pattern
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		if(pattern == null) {
			try {
				return SimpleDateFormat.getDateTimeInstance().parse(date);
			} catch (ParseException e) {
				log.error(e);
				return null;
			}
		} else {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			try {
				return format.parse(date);
			} catch (ParseException e) {
				log.error(e);
				return null;
			}
		}
	}
	
	/**
	 * get a date instance by specifying how many amount after today
	 * 
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date afterToday(int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(dateOfToday());
		c.add(field, amount);
		
		return c.getTime();
	}

	/**
	 * JAXB Date adapter implement
	 */
	public static class JaxbDateAdapter extends XmlAdapter<String, Date> {

		@Override
		public Date unmarshal(String v) throws Exception {
			return DateUtils.parseDate(v);
		}

		@Override
		public String marshal(Date v) throws Exception {
			return DateUtils.formatDate(v);
		}
	}
}