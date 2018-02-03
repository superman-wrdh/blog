package com.hc.converter.conversion;

/**
 * Double type converter
 * @author xliu6
 *
 */
public final class DoubleConverter extends NumberConverter {
	/**
	 * default value
	 */
	protected Object defaultValue() {
		return 0d;
	}

	/**
	 * value of
	 */
	protected Object valueOf(Object from) {
		return ((Number)from).doubleValue();
	}

	/**
	 * convert to BigDecimal
	 */
	protected Object convert(Class c, Object value) {
		return Double.parseDouble(Convert.toString(value).trim());
	}
}