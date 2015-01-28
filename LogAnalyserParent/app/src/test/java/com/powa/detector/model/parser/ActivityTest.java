package com.powa.detector.model.parser;

import static com.powa.detector.infra.converters.DateTimeConverter.epochToDate;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("boxing")
public class ActivityTest {

	private static final String IP = "30.212.19.125";
	private static final String EPOCH = "1420113600";
	private static final String EXACTLY_FIVE_MINS_LATER = "1420113900";
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
	
	@Test public void 
	should_record_the_action() {
		assertThat(activity.action(), is(Activity.Action.FAILURE));
	}
	
	@Test public void 
	should_record_the_username() {
		assertThat(activity.username(), is(USERNAME));
	}
	
	@Test public void 
	should_return_true_when_activity_is_failure() {
		assertThat(activity.isFailure(), is(true));
	}
	
	@Test public void 
	should_be_within_five_minutes_from_each_other() {
		Activity exactlyFiveMinsLater = new Activity(IP, EXACTLY_FIVE_MINS_LATER, ACTION, USERNAME);
		
		assertThat(exactlyFiveMinsLater.withinSuspiciousRange(activity), is(true));
	}
}
