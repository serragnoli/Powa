package com.powa.detector.model.parser;

import static com.powa.detector.model.parser.Activity.Action.valueOf;

import org.joda.time.DateTime;

import com.powa.detector.infra.converters.DateTimeConverter;

public class Activity {
	
	enum Action{SUCCESS, FAILURE}
	
	private String ip;
	private DateTime date;
	private Action action;
	private String username;

	Activity(String ip, String epoch, String action, String username) {
		this.ip = ip;
		this.date = DateTimeConverter.epochToDate(epoch);
		this.action = valueOf(action);
		this.username = username;
	}

	public String ip() {
		return ip;
	}

	public DateTime date() {
		return date;
	}

	public Action action() {
		return action;
	}

	public String username() {
		return username;
	}
}
