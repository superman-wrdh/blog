package com.hc.converter.conversion;

import com.hc.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Date converter
 * @author hc
 *
 */
public final class DateConverter implements FormattableConverter {
	private static Log logger = LogFactory.getLog(DateConverter.class);
	
	private static final List<String> patterns;
	
	static {
		patterns = new ArrayList<String>();
		patterns.add("yyyy-MM-dd");
		patterns.add("yyyy/MM/dd");
		patterns.add("yyyy MM dd");
		patterns.add("yyyy-MM-dd HH:mm:ss");
		patterns.add("yyyy/MM/dd HH:mm:ss");
		patterns.add("yyyy MM dd HH:mm:ss");
		patterns.add("E MMM dd HH:mm:ss z yyyy");
	}

	/**
	 * Convert value to Date
	 */
	public Object convertValue(Object value) {
		return convertValue(value, null);
	}

	/**
	 * convert value to date with format
	 * @param value value value
	 * @param format format
	 */
	public Object convertValue(Object value, String format) {
		if(value==null)
			return null;

		Class<?> c = value.getClass();
		if(Number.class.isAssignableFrom(c))
			return new Date(((Number)value).longValue());

		String strValue = Convert.toString(value).trim();

		Object parsed = DateUtils.parse(strValue, format);

		if(parsed != null)
			return parsed;

		for (String pattern : patterns) {
			Date dValue = parse(strValue, pattern);
			if (dValue != null)
				return dValue;
		}
		return null;
	}

	private Date parse(String value, String pattern) {
		SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();

		// try locale culture
		try {
			format.applyPattern(pattern);
			return format.parse(value);
		} catch(ParseException ex) {
			logger.error(ex);
		}

		// try US culture
		try {
			format = new SimpleDateFormat(pattern, Locale.US);
			return format.parse(value);
		} catch(ParseException ex) {
			return null;
		}
	}
}