package com.powa.detector;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TimeBasedLogAnalyzerTest {

	@Test public void 
	should_invoke_parsing_service() {
		LogAnalyzer analyzer = new TimeBasedLogAnalyzer();
		
		analyzer.parseLine("");
		
		//Needs to be mocked
	}
}
