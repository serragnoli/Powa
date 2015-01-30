package com.powa.detector.model.hackprotection;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.powa.detector.model.parser.Activity;

public class HackRegistry {
	
	private static final int SIZE_OF_SUSPICION_AWARENESS = 5;
	private static final int LIMIT_OF_TRUST = 4;
	
	private Map<String, CircularFifoQueue<Activity>> buffer = new HashMap<>();
	
	String ipIfSuspicious(Activity activity) {
		String suspiciousIp = null;
		
		if(activity.isFailure()) {
			final String ip = activity.ip();
			final CircularFifoQueue<Activity> queue = buffer.get(ip);
			
			//TODO: Replace by Chain Of Responsibility
			if(queue == null || queue.isEmpty()) {
				CircularFifoQueue<Activity> newQueue = new CircularFifoQueue<Activity>(SIZE_OF_SUSPICION_AWARENESS);
				newQueue.offer(activity);
				buffer.put(ip, newQueue);
			} else if(queue.size() < LIMIT_OF_TRUST) {
				queue.offer(activity);
			} else if(activity.withinSuspiciousRange(queue.peek())) {
				suspiciousIp = activity.ip();
				queue.add(activity);
			}
		}
		
		return suspiciousIp;		
	}

	public int size() {
		return buffer.size();
	}
}
