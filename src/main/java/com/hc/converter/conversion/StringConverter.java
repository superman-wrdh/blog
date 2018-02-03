package com.hc.converter.conversion;

import com.hc.util.DateUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * String value converter
 * @author xliu6
 *
 */
public final class StringConverter implements FormattableConverter {
	private static final Gson gson = new GsonBuilder().create();

	/**
	 * Convert value to string value
	 */
	public Object convertValue(Object value) {
	    return convertValue(value, null);
	}

    /**
     * convert value with format
     * @param value value value
     * @param format format
     */
    public Object convertValue(Object value, String format) {
        if (value == null)
            return null;
        Class<?> c = value.getClass();
        if (String.class.isAssignableFrom(c))
            return value;
        if (Character.class.isAssignableFrom(c))
            return new String(new char[]{(Character)value});
        if (UUID.class.isAssignableFrom(c))
            return value.toString();
        if (Class.class.isAssignableFrom(c)) {
            String s = value.toString();
            if(s.startsWith("class "))
                return s.substring(6);
            return s;
        }
        if(Date.class.isAssignableFrom(c)) {
            SimpleDateFormat sf = new SimpleDateFormat(format == null ? DateUtils.getDefaultTimePattern():format);
            return sf.format((Date)value);
        }
        if(Number.class.isAssignableFrom(c) || Convert.isPrimitiveNumberType(c)) {
            if(format == null)
                return value.toString();
            else {
                DecimalFormat df = new DecimalFormat(format);
                return df.format(Convert.toDouble(value));
            }
        }
        return gson.toJson(value);
    }
}