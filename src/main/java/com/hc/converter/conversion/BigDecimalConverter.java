package com.hc.converter.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigDecimal type converter
 * @author hc
 *
 */
public final class BigDecimalConverter extends NumberConverter {

	/**
	 * default value
	 */
	protected Object defaultValue() {
		return 0L;
	}

	/**
	 * value of
	 */
	protected Object valueOf(Object from) {
		return BigDecimal.valueOf((Double) from);
	}

	/**
	 * convert to BigDecimal
	 */
	protected Object convert(Class c, Object value) {
		if(BigInteger.class.isAssignableFrom(c))
			return new BigDecimal((BigInteger)value);
		return new BigDecimal(Convert.toString(value));
	}
}