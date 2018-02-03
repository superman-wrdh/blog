package com.hc.converter.conversion;

/**
 * Long type converter
 * @author xliu6
 *
 */
public final class LongConverter extends NumberConverter {
	/**
	 * default value
	 */
	protected Object defaultValue() {
		return 0;
	}

	/**
	 * value of
	 */
	protected Object valueOf(Object from) {
		return ((Number)from).longValue();
	}

	/**
	 * convert to BigDecimal
	 */
	protected Object convert(Class c, Object value) {
		return Long.parseLong(Convert.toString(value).trim());
	}
}