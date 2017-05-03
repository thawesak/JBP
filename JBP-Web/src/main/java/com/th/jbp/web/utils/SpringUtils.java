package com.th.jbp.web.utils;

import org.zkoss.zkplus.spring.SpringUtil;

public class SpringUtils {
	private SpringUtils() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T lookupBean(Class<T> clazz, String beanId) {
		return (T) SpringUtil.getBean(beanId);
	}
}
