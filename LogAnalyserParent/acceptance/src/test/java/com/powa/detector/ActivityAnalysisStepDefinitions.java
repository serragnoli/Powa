package com.powa.detector;

import java.util.List;

import com.powa.detector.model.parser.ParserService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ActivityAnalysisStepDefinitions {

	private LogAnalyzer analyser;
	private String result;
	
	@Before public void 
	setup() {
		ParserService parserService = new ParserService();
		analyser = new TimeBasedLogAnalyzer(parserService);
	}
	
	@Given("^the following activities$") public void 
	receive_line(List<String> activities) {
		for (String activity : activities) {
			analyser.parseLine(activity);
		}
	}
	
	@When("^the activity \"([^\"]*)\" is processed$") public void
	invoke_analyzer(String activity) {
		result = analyser.parseLine(activity);
	}
	
	@Then("^the analyser will return \"([^\"]*)\"") public void
	validate_return(String expected) {
		assertThat(result, is(expected));
	}
}
