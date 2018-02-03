package com.hc.converter.conversion;

/**
 * Value converter interface
 * @author xliu6
 *
 */
public interface TypeConverter {
	/**
	 * Convert value to specified type
	 * @param value value
	 * @return converted value
	 */
	public Object convertValue(Object value);
}
