package com.hc.converter.conversion;

/**
 * Integer type converter
 * @author xliu6
 *
 */
public final class IntegerConverter extends NumberConverter {
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
		return ((Number)from).intValue();
	}

	/**
	 * convert to BigDecimal
	 */
	protected Object convert(Class c, Object value) {
		return Integer.parseInt(Convert.toString(value).trim());
	}
}