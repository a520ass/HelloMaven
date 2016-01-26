package com.hf.spring.jpa.common.converters;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateConverter implements Converter<String, Date>{
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
	
	@Override
	public Date convert(String source) {
		// TODO Auto-generated method stub
		if (source != null) {
			try {
				return DateUtils.parseDate(source,parsePatterns);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

}
