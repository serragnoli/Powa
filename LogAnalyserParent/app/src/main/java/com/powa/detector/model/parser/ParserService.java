package com.powa.detector.model.parser;

public class ParserService {

	public Activity parse(String line) {
		if(line == null) {
			throw new IllegalArgumentException("Line to be parsed cannot be null");
		}
		
		String[] tokens = line.split(",");
		return new Activity(tokens[0]);
	}
}
