package com.powa.detector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.powa.detector.model.hackprotection.HackProtectionService;
import com.powa.detector.model.hackprotection.HackRegistry;
import com.powa.detector.model.parser.ParserService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ActivityAnalysisStepDefinitions {

	private LogAnalyzer analyser;
	private String result;
	
	@Before public void 
	setup() {
		ParserService parserService = new ParserService();
		HackRegistry registry = new HackRegistry();
		HackProtectionService hackProtectionService = new HackProtectionService(registry);
		analyser = new TimeBasedLogAnalyzer(parserService, hackProtectionService);
	}
	
	@Given("^the following activities$") public void 
	receive_line(List<String> lines) {
		if(lines == null || lines.isEmpty()) {
			return;
		}
		
		for (String line : lines) {
			analyser.parseLine(line);
		}
	}
	
	@When("^the activity \"([^\"]*)\" is processed$") public void
	invoke_analyzer(String line) {
		result = analyser.parseLine(line);
	}
	
	@Then("^the analyser will return \"([^\"]*)\"") public void
	validate_return(String expected) {
		String myExpected = expected;
		//hack to make it null since it's unknown how to pass null in Cucumber
		if(expected.equals("null")) {
			myExpected = null;
		}
		assertThat(result, is(myExpected));
	}
}
