package com.powa.detector;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.powa.detector.model.parser.ParserService;

public class TimeBasedLogAnalyzerTest {
	
	private LogAnalyzer analyzer;
	private ParserService parserService;
	
	@Before public void 
	setup() {
		parserService = Mockito.mock(ParserService.class);	
		analyzer = new TimeBasedLogAnalyzer(parserService);
	}
	
	@Test public void 
	should_invoke_parsing_service() {
		analyzer.parseLine("");
		
		verify(parserService).parse(anyString());
	}	
}
