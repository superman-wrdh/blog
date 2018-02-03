package com.hc.converter.conversion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class converter
 * @author xliu6
 *
 */
public class ClassConverter implements TypeConverter {
	static final Log log = LogFactory.getLog(ClassConverter.class);
	/**
	 * converter value to class
	 */
	public Object convertValue(Object value) {
		if(value==null)
			return null;
		Class<?> c = value.getClass();
		if(c==Class.class)
			return value;
		if(c==String.class)
			try {
				String strValue = (String)value;
				if(strValue.startsWith("class "))
					strValue = strValue.substring(6);
				return Class.forName(strValue);
			} catch (ClassNotFoundException e) {
				log.error("Class not found : "+value,e);
				return null;
			}
		
		return null;
	}

}
