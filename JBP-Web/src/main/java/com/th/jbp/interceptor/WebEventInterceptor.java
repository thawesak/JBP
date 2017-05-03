package com.th.jbp.interceptor;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.EventInterceptor;

public class WebEventInterceptor implements EventInterceptor {

	/*
	 * private static final Logger LOGGER = Logger
	 * .getLogger(DQEventInterceptor.class);
	 */

	@Override
	public Event beforeSendEvent(Event event) {

		/*
		 * if (event.getName().endsWith("onClick")) {
		 * 
		 * }
		 */
		return event;
	}

	@Override
	public Event beforePostEvent(Event event) {
		/*
		 * if (event.getName().endsWith("onClick")) {
		 * LOGGER.debug("DQEventInterceptor:beforePostEvent->" + "showBusy");
		 * Clients.showBusy(null); }
		 */
		return event;
	}

	@Override
	public Event beforeProcessEvent(Event event) {
		/*
		 * if (event.getName().endsWith("onClick")) {
		 * LOGGER.debug("DQEventInterceptor:beforeProcessEvent");
		 * 
		 * }
		 */
		return event;
	}

	@Override
	public void afterProcessEvent(Event event) {
		if (event.getName().endsWith("onClick")) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
		}

	}

}
