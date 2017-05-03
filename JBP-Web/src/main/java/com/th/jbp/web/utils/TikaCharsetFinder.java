package com.th.jbp.web.utils;

import java.io.IOException;
import java.io.InputStream;

import org.zkoss.zk.ui.util.CharsetFinder;

public class TikaCharsetFinder implements CharsetFinder {
	// private static final Logger LOGGER =
	// Logger.getLogger(TikaCharsetFinder.class);

	@Override
	public String getCharset(String contentType, InputStream content) throws IOException {
		return "utf-8";
	}
}