package com.powa.detector.model.hackprotection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.powa.detector.model.parser.Activity;

@SuppressWarnings("boxing")
public class HackRegistryTest {

	private static final Activity SUCCESSFUL_ACTIVITY = new Activity("30.212.19.124","300","SUCCESS","Thomas.Davenport");
	private static final Activity FAILED_ACTIVITY = new Activity("30.212.19.125","300","FAILURE","John.Doe");
	
	private HackRegistry registry;
	
	@Before public void 
	setup() {
		registry = new HackRegistry();
	}
	
	@Test public void 
	should_ignore_successful_activity() {
		String suspiciousIp = registry.ipIfSuspicious(SUCCESSFUL_ACTIVITY);
		
		assertThat(suspiciousIp, is(nullValue()));
	}
	
	@Test public void 
	registry_should_be_empty_when_successful_activity() {
		registry.ipIfSuspicious(SUCCESSFUL_ACTIVITY);
		
		assertThat(registry.size(), is(0));
	}
	
	@Test public void 
	registry_should_have_element_when_failed_activity() {
		registry.ipIfSuspicious(FAILED_ACTIVITY);
		
		assertThat(registry.size(), is(1));
	}
	
	@Test public void 
	should_return_ip_when_fifth_attempt_within_fve_minutes() {
		createAndAddFiveFailedActivitiesToRegistry();

		String suspiciousIp = registry.ipIfSuspicious(FAILED_ACTIVITY);
		
		assertThat(suspiciousIp, is(FAILED_ACTIVITY.ip()));
	}

	private void createAndAddFiveFailedActivitiesToRegistry() {
		Activity fourMinEarlier = new Activity("30.212.19.125","60","FAILURE","John.Doe");
		Activity threeMinEarlier = new Activity("30.212.19.125","120","FAILURE","John.Doe");
		Activity twoMinEarlier = new Activity("30.212.19.125","180","FAILURE","John.Doe");
		Activity oneMinEarlier = new Activity("30.212.19.125","240","FAILURE","John.Doe");

		registry.ipIfSuspicious(fourMinEarlier);
		registry.ipIfSuspicious(threeMinEarlier);
		registry.ipIfSuspicious(twoMinEarlier);
		registry.ipIfSuspicious(oneMinEarlier);
	}
}
