package com.th.jbp.web.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class WebSessionListener implements HttpSessionListener {

	private static final Logger LOG = Logger.getLogger(WebSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		LOG.debug(String.format("Session created user info = %s", event.getSession().getId()));
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {

		LOG.debug(String.format("session destroyed user info = %s", event.getSession().getId()));

	}

}