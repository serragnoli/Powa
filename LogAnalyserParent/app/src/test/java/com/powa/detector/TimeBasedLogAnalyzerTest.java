package com.powa.detector;


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.powa.detector.model.hackprotection.HackProtectionService;
import com.powa.detector.model.parser.Activity;
import com.powa.detector.model.parser.ParserService;

public class TimeBasedLogAnalyzerTest {
	
	private LogAnalyzer analyzer;
	private ParserService parserService;
	private HackProtectionService hackProtectionService;
	
	@Before public void 
	setup() {
		parserService = mock(ParserService.class);
		hackProtectionService = mock(HackProtectionService.class);
		analyzer = new TimeBasedLogAnalyzer(parserService, hackProtectionService);
	}
	
	@Test public void 
	should_invoke_parsing_service() {
		analyzer.parseLine("");
		
		verify(parserService).parse(anyString());
	}
	
	@Test public void 
	should_invoke_hack_protection_service() {
		analyzer.parseLine("");
		
		verify(hackProtectionService).analyse(any(Activity.class));
	}
}
