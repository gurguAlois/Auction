package com.sda.auction.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class DateConverter {

	private String dateFormat = "yyyy-MM-dd";
	private String dateFormat2 = "dd-MM-yyyy";

	public Date parse(String dateAsString) {
		SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
		try {
			return dateFormater.parse(dateAsString);
		} catch (ParseException e) {
			return null;
		}
	}

	public String format(Date date) {
		SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat2);
		return dateFormater.format(date);
	}
}
