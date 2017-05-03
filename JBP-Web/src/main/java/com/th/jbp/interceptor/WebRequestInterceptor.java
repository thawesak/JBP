package com.th.jbp.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.RequestInterceptor;

public class WebRequestInterceptor implements RequestInterceptor {

	private static final Logger LOGGER = Logger.getLogger(WebRequestInterceptor.class);

	@Override
	public void request(Session sess, Object request, Object response) {
		LOGGER.debug("DQRequestInterceptor : request-> " + ToStringBuilder.reflectionToString(request));
		HttpServletRequest r = (HttpServletRequest) request;
		Enumeration<?> params = r.getParameterNames();
		while (params.hasMoreElements()) {
			String p = (String) params.nextElement();
			LOGGER.debug("DQRequestInterceptor : request-> " + p + " =" + r.getParameter(p));
		}
	}

}
