package com.powa.detector.model.parser;

import static com.powa.detector.infra.converters.DateTimeConverter.epochToDate;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ActivityTest {

	private static final String IP = "30.212.19.125";
	private static final String EPOCH = "1420113600";
	private static final String ACTION = "FAILURE";
	private static final String USERNAME = "John.Doe";
	
	private Activity activity;
	
	@Before public void 
	setup() {
		activity = new Activity(IP, EPOCH, ACTION, USERNAME);
	}
	
	@Test public void 
	should_record_the_ip() {
		assertThat(activity.ip(), is(IP));
	}
	
	@Test public void 
	should_record_the_epoch() {
		assertThat(activity.date(), is(epochToDate(EPOCH)));
	}
}
