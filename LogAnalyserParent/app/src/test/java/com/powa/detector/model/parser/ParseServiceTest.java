package com.powa.detector.model.parser;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Assert;
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
		
		Assert.assertThat(activity, is(notNullValue(Activity.class)));
	}
}
