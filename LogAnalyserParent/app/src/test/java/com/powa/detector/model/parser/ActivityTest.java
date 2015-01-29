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
	private static final String FAILURE = "FAILURE";
	private static final String SUCCESS = "SUCCESS";
	private static final String USERNAME = "John.Doe";
	
	private Activity failedActivity;
	
	@Before public void 
	setup() {
		failedActivity = new Activity(IP, EPOCH, FAILURE, USERNAME);
	}
	
	@Test public void 
	should_record_the_ip() {
		assertThat(failedActivity.ip(), is(IP));
	}
	
	@Test public void 
	should_record_the_epoch() {
		assertThat(failedActivity.date(), is(Long.valueOf(EPOCH)));
	}
	
	@Test public void 
	should_record_the_action() {
		assertThat(failedActivity.action(), is(Activity.Action.FAILURE));
	}
	
	@Test public void 
	should_record_the_username() {
		assertThat(failedActivity.username(), is(USERNAME));
	}
	
	@Test public void 
	should_return_true_when_activity_is_failure() {
		assertThat(failedActivity.isFailure(), is(true));
	}
	
	@Test public void 
	should_return_false_when_activity_is_success() {
		Activity successActivity = new Activity(IP, EPOCH, SUCCESS, USERNAME);
		
		assertThat(successActivity.isFailure(), is(false));
	}
	
	@Test public void 
	should_arise_suspicion_when_both_activities_have_the_same_epoch() {
		Activity sameEpoch = new Activity(IP, EPOCH, FAILURE, USERNAME);
		
		assertThat(sameEpoch.withinSuspiciousRange(failedActivity), is(SUSPICIOUS));
	}
	
	@Test public void 
	should_arise_suspicion_as_it_is_exactly_five_minutes_apart_from_each_other() {
		Activity exactlyFiveMinsLater = new Activity(IP, UPPER_BOUNDARY_OF_SUSPICION_ZONE, FAILURE, USERNAME);
		
		assertThat(exactlyFiveMinsLater.withinSuspiciousRange(failedActivity), is(SUSPICIOUS));
	}
	
	@Test public void 
	should_arise_suspicion_as_it_is_exactly_five_minutes_apart_from_each_other_inverted() {
		Activity exactlyFiveMinsLater = new Activity(IP, UPPER_BOUNDARY_OF_SUSPICION_ZONE, FAILURE, USERNAME);
		
		assertThat(failedActivity.withinSuspiciousRange(exactlyFiveMinsLater), is(SUSPICIOUS));		
	}
	
	@Test public void 
	should_be_normal_if_activity_one_second_greater_than_five_minutes_apart_from_each_other() {
		Activity oneSecondBeforeSuspicionZone = new Activity(IP, ONE_SECOND_AFTER_SUSPICION_ZONE, FAILURE, USERNAME);
		
		assertThat(failedActivity.withinSuspiciousRange(oneSecondBeforeSuspicionZone), is(NORMAL));
	}
}
