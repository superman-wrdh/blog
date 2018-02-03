package com.hc.converter.conversion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Number converter base class
 *
 * 2016/10/09: fixed the bug when format is null
 */
public abstract class NumberConverter implements FormattableConverter {
    private static final Log logger = LogFactory.getLog(NumberConverter.class);

    /**
     * Convert value to big decimal
     */
    public Object convertValue(Object value) {
        return convertValue(value, null);
    }

    /**
     * Convert value to big decimal
     */
    public Object convertValue(Object value, String format) {
        if(value==null)
            return defaultValue();
        Class<?> c = value.getClass();
        if(Number.class.isAssignableFrom(c))
            return valueOf(value);
        if(String.class.isAssignableFrom(c)) {
            String strValue = Convert.toString(value);
            DecimalFormat df = format == null ? new DecimalFormat()
                    : new DecimalFormat(format);

            try {
                return valueOf(df.parse(strValue));
            } catch (ParseException e) {
                logger.error(e);
                return valueOf(value);
            }
        }
        if(Boolean.class.isAssignableFrom(c))
            return ((Boolean)value) ? valueOf(1) : valueOf(0);
        if(Character.class.isAssignableFrom(c))
            return valueOf(value);

        return convert(c, value);
    }

    protected abstract Object defaultValue();

    protected abstract Object valueOf(Object from);

    protected abstract Object convert(Class c, Object value);
}