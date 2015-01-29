package com.powa.detector.model.parser;

import static com.powa.detector.model.parser.Activity.Action.FAILURE;
import static com.powa.detector.model.parser.Activity.Action.valueOf;

public class Activity {
	
	enum Action{SUCCESS, FAILURE}
	
	private static final int SUSPICION_AWARENESS_AT = 300;
	
	private String ip;
	private Long epoch;
	private Action action;
	private String username;

	public Activity(String ip, String epoch, String action, String username) {
		this.ip = ip;
		this.epoch = Long.valueOf(epoch);
		this.action = valueOf(action);
		this.username = username;
	}

	public String ip() {
		return ip;
	}

	public Long date() {
		return epoch;
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
		long difference = epoch.longValue() - ofActivity.date().longValue();
		long absoluteDifference = Math.abs(difference);
		
		boolean arisenSuspicion = absoluteDifference <= SUSPICION_AWARENESS_AT;
		
		return arisenSuspicion;
	}
}
