package com.powa.detector.model.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("boxing")
public class ActivityTest {
	
	private static final Boolean SUSPICIOUS = Boolean.TRUE;
	private static final Boolean NORMAL = Boolean.FALSE;
	private static final String IP = "30.212.19.125";
	private static final String EPOCH = "1420113600";
	private static final String UPPER_BOUNDARY_OF_SUSPICION_ZONE = "1420113900";
	private static final String ONE_SECOND_AFTER_SUSPICION_ZONE = "1420113901";
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
		assertThat(activity.date(), is(Long.valueOf(EPOCH)));
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
	should_arise_suspicion_when_both_activities_have_the_same_epoch() {
		Activity sameEpoch = new Activity(IP, EPOCH, ACTION, USERNAME);
		
		assertThat(sameEpoch.withinSuspiciousRange(activity), is(SUSPICIOUS));
	}
	
	@Test public void 
	should_arise_suspicion_as_it_is_exactly_five_minutes_apart_from_each_other() {
		Activity exactlyFiveMinsLater = new Activity(IP, UPPER_BOUNDARY_OF_SUSPICION_ZONE, ACTION, USERNAME);
		
		assertThat(exactlyFiveMinsLater.withinSuspiciousRange(activity), is(SUSPICIOUS));
	}
	
	@Test public void 
	should_arise_suspicion_as_it_is_exactly_five_minutes_apart_from_each_other_inverted() {
		Activity exactlyFiveMinsLater = new Activity(IP, UPPER_BOUNDARY_OF_SUSPICION_ZONE, ACTION, USERNAME);
		
		assertThat(activity.withinSuspiciousRange(exactlyFiveMinsLater), is(SUSPICIOUS));		
	}
	
	@Test public void 
	should_be_normal_if_activity_one_second_greater_than_five_minutes_apart_from_each_other() {
		Activity oneSecondBeforeSuspicionZone = new Activity(IP, ONE_SECOND_AFTER_SUSPICION_ZONE, ACTION, USERNAME);
		
		assertThat(activity.withinSuspiciousRange(oneSecondBeforeSuspicionZone), is(NORMAL));
	}
}
