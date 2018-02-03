package com.hc.converter.conversion;

/**
 * Byte type converter
 * @author xliu6
 *
 */
public final class ByteConverter implements TypeConverter {

	/**
	 * Convert value to byte
	 */
	public Object convertValue(Object value) {
		if(value==null)
			return 0;
		Class<?> c = value.getClass();
		if(Number.class.isAssignableFrom(c))
			return ((Number)value).byteValue();
		if(Boolean.class.isAssignableFrom(c))
			return ((Boolean)value)?1:0;
		if(Character.class.isAssignableFrom(c))
			return (byte)((Character)value).charValue();
		return Byte.parseByte(Convert.toString(value).trim());
	}
}