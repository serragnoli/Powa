package com.powa.detector.model.hackprotection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.powa.detector.model.parser.Activity;

@SuppressWarnings("boxing")
public class HackRegistryTest {

	private static final Activity SUCCESSFUL_ACTIVITY = new Activity("30.212.19.124","1336129421","SUCCESS","Thomas.Davenport");;
	private HackRegistry registry = new HackRegistry();
	
	@Test public void 
	should_ignore_successful_activity() {
		String suspiciousIp = registry.ipIfSuspicious(SUCCESSFUL_ACTIVITY);
		
		assertThat(suspiciousIp, is(nullValue()));
	}
	
	@Test public void 
	registry_should_be_empty_when_succeeful_activity() {
		registry.ipIfSuspicious(SUCCESSFUL_ACTIVITY);
		
		assertThat(registry.size(), is(0));
	}	
}
