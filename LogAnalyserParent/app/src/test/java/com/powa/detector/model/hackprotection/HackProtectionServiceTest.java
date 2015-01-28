package com.powa.detector.model.hackprotection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.Test;

import com.powa.detector.model.parser.Activity;

public class HackProtectionServiceTest {
	
	private static final Activity SUCCESSFUL_ACTIVITY = new Activity("30.212.19.123","1336129421","FAILURE","Thomas.Davenport");
	private static final Activity FAILED_ACTIVITY = new Activity("30.212.19.124","1336129421","SUCCESS","Thomas.Davenport");
	
	private HackRegistry registry;
	private HackProtectionService hackProtectionService;

	@Before public void 
	setup() {
		registry = mock(HackRegistry.class);
		hackProtectionService = new HackProtectionService(registry);		
	}
	
	@Test public void 
	should_return_null_when_action_was_successful() {
		String suspiciousIp = hackProtectionService.analyse(SUCCESSFUL_ACTIVITY);
		
		assertThat(suspiciousIp, is(nullValue()));
	}
	
	@Test public void 
	should_invoke_hack_registry_when_action_was_a_failure() {
		hackProtectionService.analyse(FAILED_ACTIVITY);
		
		verify(registry).ipIfSuspicious(any(Activity.class));
	}
}
