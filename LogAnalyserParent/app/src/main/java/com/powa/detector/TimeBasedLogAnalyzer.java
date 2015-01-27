package com.powa.detector;

import com.powa.detector.model.parser.ParserService;

public class TimeBasedLogAnalyzer implements LogAnalyzer {

	private ParserService parserService;

	public TimeBasedLogAnalyzer(ParserService parserService) {
		this.parserService = parserService;
	}

	@Override
	public String parseLine(String line) {
		parserService.parse(line);
		
		return null;
	}
}
