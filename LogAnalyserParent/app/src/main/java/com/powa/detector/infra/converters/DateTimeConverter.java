package com.powa.detector.infra.converters;

import static java.lang.Long.parseLong;

import org.joda.time.DateTime;

public class DateTimeConverter {

	public static long epochToMillis(String epoch) {
		final DateTime dateTime = new DateTime(parseLong(epoch) * 1000);
		
		return dateTime.getMillis();
	}
	
	public static DateTime epochToDate(String epoch) {
		return new DateTime(epochToMillis(epoch));
	}
}
