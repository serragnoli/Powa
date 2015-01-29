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
	private static final Activity OTHER_FAILED_ACTIVITY = new Activity("30.212.19.126","300","FAILURE","John.Doe");
	
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
	should_return_ip_when_fifth_attempt_within_five_minutes() {
		addFailedActivitiesToRegistry(4);

		String suspiciousIp = registry.ipIfSuspicious(FAILED_ACTIVITY);
		
		assertThat(suspiciousIp, is(FAILED_ACTIVITY.ip()));
	}
	
	@Test public void 
	should_return_ip_when_sixth_attempt_within_five_minutes() {
		addFailedActivitiesToRegistry(5);
		
		String suspiciousIp = registry.ipIfSuspicious(FAILED_ACTIVITY);
		
		assertThat(suspiciousIp, is(FAILED_ACTIVITY.ip()));
	}
	
	@Test public void 
	should_be_considered_normal_when_fifth_activity_is_different_ip() {
		addFailedActivitiesToRegistry(4);
		
		String suspiciousIp = registry.ipIfSuspicious(OTHER_FAILED_ACTIVITY);
		
		assertThat(suspiciousIp, is(nullValue()));
	}
	
	@Test public void 
	registry_should_have_two_elements_when_two_different_ips_have_failed_attempts() {
		addFailedActivitiesToRegistry(4);
		
		registry.ipIfSuspicious(OTHER_FAILED_ACTIVITY);
		
		assertThat(registry.size(), is(2));
	}

	private void addFailedActivitiesToRegistry(int qty) {
		long epoch = 60;
		
		for (int i = 0; i < qty; i++) {
			Activity activity = new Activity("30.212.19.125",Long.toString(epoch++),"FAILURE","John.Doe");
			registry.ipIfSuspicious(activity);			
		}
	}
}
