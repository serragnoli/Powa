package com.powa.detector.model.parser;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ParseServiceTest {
	
	private static final String LINE = "30.212.19.124,1336129421,SUCCESS,Thomas.Davenport";
	
	private ParserService parserService;
	
	@Before public void 
	setup() {
		parserService = new ParserService();
	}
	
	@Test public void 
	should_return_an_activity() {
		Activity activity = parserService.parse(LINE);
		
		assertThat(activity, is(notNullValue(Activity.class)));
	}
	
	@Test public void 
	should_return_an_activity_with_date() {
		Activity activity = parserService.parse(LINE);
		
		assertThat(activity.ip(), is(notNullValue()));
	}
	
	@Test public void 
	should_return_an_activity_with_epoch() {
		Activity activity = parserService.parse(LINE);
		
		assertThat(activity.date(), is(notNullValue()));
	}
	
	@Test public void 
	should_return_an_activity_with_action() {
		Activity activity = parserService.parse(LINE);
		
		assertThat(activity.action(), is(notNullValue()));
	}
	
	@Test public void 
	should_return_an_activity_with_username() {
		Activity activity = parserService.parse(LINE);
		
		assertThat(activity.username(), is(notNullValue()));
	}
	
	@Test(expected = IllegalArgumentException.class) public void 
	should_throw_exception_when_line_is_null() {
		parserService.parse(null);
	}
}
