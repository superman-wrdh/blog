package com.hc.converter.conversion;

/**
 * Character converter
 * @author xliu6
 *
 */
public class CharacterConverter implements TypeConverter {

	/**
	 * Convert value to char
	 */
	public Object convertValue(Object value) {
		if(value==null)
			return (char)0;
		Class<?> c = value.getClass();
		if(Number.class.isAssignableFrom(c)) {
			short s = Convert.toShort(value);
			return (char)s;
		}
		
		String str = Convert.toString(value);
		
		return str.length()>0?str.charAt(0):(char)0;
	}

}
