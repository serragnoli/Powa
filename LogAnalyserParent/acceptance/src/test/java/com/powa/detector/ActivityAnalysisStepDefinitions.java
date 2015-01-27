package com.powa.detector;

import java.util.List;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ActivityAnalysisStepDefinitions {

	private LogAnalyzer analyser;
	
	@Before public void 
	setup() {
		analyser = new TimeBasedLogAnalyzer();
	}
	
	@Given("^the following activities$") public void 
	receive_line(List<String> lines) {
		System.out.println(lines);
	}
	
	@When("^the line is processed$") public void
	invoke_analyzer() {
		System.out.println("Processing");
	}
	
	@Then("^the analyser will return \"([^\"]*)\"") public void
	validate_return(String expected) {
		System.out.println(expected);
	}
}
