package com.th.jbp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AuthenticationFilter implements Filter {

	// private static final Logger LOG =
	// Logger.getLogger(AuthenticationFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		chain.doFilter(req, res);

	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}

}
