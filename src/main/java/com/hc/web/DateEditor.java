package com.hc.web;

import com.hc.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.util.Date;

/**
 * custom date editor
 * @author hc
 *
 */
public class DateEditor extends PropertiesEditor {
	private String pattern;
	
	public DateEditor(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public void setAsText(String value) throws IllegalArgumentException {
		if(StringUtils.isBlank(value))
			setValue(null);
		else
			setValue(DateUtils.parse(value, pattern));
	}
	
	@Override
	public String getAsText() {
		Object value = getValue();
		
		return value==null?null: DateUtils.format((Date)value, pattern);
	}
}