package com.powa.detector.infra.converters;

import static com.powa.detector.infra.converters.DateTimeConverter.epochToDate;
import static com.powa.detector.infra.converters.DateTimeConverter.epochToMillis;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

@Deprecated
@SuppressWarnings("boxing")
public class DateTimeConverterTest {

	private static final String DEC_25th_2000_AT_MIDNIGHT_EPOCH = "977702400";
	private static final DateTime DEC_25th_2000_AT_MIDNIGHT_DATE = new DateTime(2000, 12, 25, 0, 0, 0);
	
	@Test public void 
	should_convert_epoch_to_millis() {
		assertThat(epochToMillis(DEC_25th_2000_AT_MIDNIGHT_EPOCH), is(DEC_25th_2000_AT_MIDNIGHT_DATE.getMillis()));
	}
	
	@Test public void 
	should_convert_epoch_to_date() {
		assertThat(epochToDate(DEC_25th_2000_AT_MIDNIGHT_EPOCH), is(DEC_25th_2000_AT_MIDNIGHT_DATE));
	}
}
