package com.powa.detector.model.parser;

import static com.powa.detector.model.parser.Activity.Action.FAILURE;
import static com.powa.detector.model.parser.Activity.Action.valueOf;
import static org.joda.time.Duration.millis;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.powa.detector.infra.converters.DateTimeConverter;

public class Activity {
	
	enum Action{SUCCESS, FAILURE}
	
	private static final int MILLIS_FOR_SUSPICION_AWARENESS = 300000;
	
	private String ip;
	private DateTime date;
	private Action action;
	private String username;

	public Activity(String ip, String epoch, String action, String username) {
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

	public boolean isFailure() {
		return action == FAILURE;
	}

	public boolean withinSuspiciousRange(Activity ofActivity) {
		long millisDifference = date.getMillis() - ofActivity.date().getMillis();
		
		Duration difference = Duration.millis(Math.abs(millisDifference));
		
		return difference.isShorterThan(millis(MILLIS_FOR_SUSPICION_AWARENESS)) ||
				difference.isEqual(millis(MILLIS_FOR_SUSPICION_AWARENESS));
	}
}
