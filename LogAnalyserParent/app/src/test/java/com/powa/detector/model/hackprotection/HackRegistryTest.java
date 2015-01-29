package com.powa.detector.model.hackprotection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.powa.detector.model.parser.Activity;

@SuppressWarnings("boxing")
public class HackRegistryTest {

	private static final Activity SUCCESSFUL_ACTIVITY = new Activity("30.212.19.124","1336129421","SUCCESS","Thomas.Davenport");
	private static final Activity FAILED_ACTIVITY = new Activity("30.212.19.125","1336129422","FAILURE","John.Doe");
	
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
}
