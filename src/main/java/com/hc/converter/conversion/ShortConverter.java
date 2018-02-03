package com.hc.converter.conversion;

/**
 * Short type converter
 * @author xliu6
 *
 */
public final class ShortConverter extends NumberConverter {
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
		return ((Number)from).shortValue();
	}

	/**
	 * convert to BigDecimal
	 */
	protected Object convert(Class c, Object value) {
		return Short.parseShort(Convert.toString(value).trim());
	}
}