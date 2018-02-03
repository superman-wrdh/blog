package com.hc.converter.conversion;

/**
 * Float type converter
 * @author xliu6
 *
 */
public final class FloatConverter extends NumberConverter {
	/**
	 * default value
	 */
	protected Object defaultValue() {
		return 0f;
	}

	/**
	 * value of
	 */
	protected Object valueOf(Object from) {
		return ((Number)from).floatValue();
	}

	/**
	 * convert to BigDecimal
	 */
	protected Object convert(Class c, Object value) {
		return Float.parseFloat(Convert.toString(value).trim());
	}
}