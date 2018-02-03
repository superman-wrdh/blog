package com.hc.converter.conversion;

/**
 * Boolean value converter
 * @author xliu6
 *
 */
public class BooleanConverter implements TypeConverter {

	/**
	 * Convert value to boolean value
	 */
	public Object convertValue(Object value) {
        if (value == null)
            return false;
        Class<?> c = value.getClass();
        if (Boolean.class.isAssignableFrom(c))
            return value;
        if (Number.class.isAssignableFrom(c))
            return ((Number) value).doubleValue() != 0;
        if (Character.class.isAssignableFrom(c))
            return value.equals('T') || value.equals('t') || value.equals('1');
        if(String.class.isAssignableFrom(c)) {
        	String str = ((String)value).toLowerCase();
        	return str.equals("t") || str.equals("true") || str.equals("1");
        }
        return false;
	}
}