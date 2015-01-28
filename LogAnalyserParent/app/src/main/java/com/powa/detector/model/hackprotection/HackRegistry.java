package com.powa.detector.model.hackprotection;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.powa.detector.model.parser.Activity;

public class HackRegistry {
	
	private static final int SIZE_OF_SUSPICION_AWARENESS = 5;
	
	private Map<String, CircularFifoQueue<Activity>> buffer = new HashMap<>();
	
	String ipIfSuspicious(Activity activity) {
		if(activity.isFailure()) {
			final String ip = activity.ip();
			final CircularFifoQueue<Activity> queue = buffer.get(ip);
			
			if(queue == null) {
				CircularFifoQueue<Activity> newQueue = new CircularFifoQueue<Activity>(SIZE_OF_SUSPICION_AWARENESS);
				newQueue.offer(activity);
				buffer.put(ip, newQueue);
			} else {
				if(queue.isFull() && activity.withinSuspiciousRange(queue.peek())) {
					// Continue Here
				}
			}
		}
		return null;		
	}

	public int size() {
		return buffer.size();
	}
}
