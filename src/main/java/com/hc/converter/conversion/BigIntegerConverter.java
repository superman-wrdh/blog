package com.hc.converter.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigInteger type converter
 * @author hc
 *
 */
public final class BigIntegerConverter extends NumberConverter {
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
		return BigDecimal.valueOf(((Number)from).longValue());
	}

	/**
	 * convert to BigDecimal
	 */
	protected Object convert(Class c, Object value) {
		if(BigDecimal.class.isAssignableFrom(c))
			return ((BigDecimal)value).toBigInteger();
		return new BigInteger(Convert.toString(value).trim());
	}
}