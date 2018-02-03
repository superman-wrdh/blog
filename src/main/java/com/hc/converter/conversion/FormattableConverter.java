package com.hc.converter.conversion;

/**
 * formatable converter
 */
public interface FormattableConverter extends TypeConverter {

    /**
     * Convert value to specified type
     * @param value value
     * @return converted value
     */
    public Object convertValue(Object value, String format);
}