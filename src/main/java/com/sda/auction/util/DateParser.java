package com.sda.auction.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class DateParser {

	private String dateFormat = "yyyy-MM-dd";

	public Date parse(String dateAsString) {
		SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
		try {
			return dateFormater.parse(dateAsString);
		} catch (ParseException e) {
			return null;
		}
	}

}
