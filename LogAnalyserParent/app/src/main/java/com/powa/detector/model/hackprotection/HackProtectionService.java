package com.powa.detector.model.hackprotection;

import com.powa.detector.model.parser.Activity;

public class HackProtectionService {

	private HackRegistry registry;

	public HackProtectionService(HackRegistry registry) {
		this.registry = registry;
	}

	public String analyse(Activity activity) {
		return registry.ipIfSuspicious(activity);
	}
}
