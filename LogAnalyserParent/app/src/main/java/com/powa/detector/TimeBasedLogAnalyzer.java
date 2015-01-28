package com.powa.detector;

import com.powa.detector.model.hackprotection.HackProtectionService;
import com.powa.detector.model.parser.Activity;
import com.powa.detector.model.parser.ParserService;

public class TimeBasedLogAnalyzer implements LogAnalyzer {

	private ParserService parserService;
	private HackProtectionService hackProtectionService;

	public TimeBasedLogAnalyzer(ParserService parserService, HackProtectionService hackProtectionService) {
		this.parserService = parserService;
		this.hackProtectionService = hackProtectionService;
	}

	@Override
	public String parseLine(String line) {
		Activity activity = parserService.parse(line);
		
		hackProtectionService.analyse(activity);
		
		return null;
	}
}
