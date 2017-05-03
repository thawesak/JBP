package com.th.jbp.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ValidateUtil {
	private ValidateUtil() {
	}

	public static boolean isThaiDocIdCheckSumFormat(String id) {
		if (StringUtils.length(id) != 13)
			return false;
		if (!NumberUtils.isDigits(id))
			return false;
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			sum += NumberUtils.toInt(String.valueOf(id.charAt(i))) * (13 - i);
		}
		if ((11 - sum % 11) % 10 != NumberUtils.toInt(String.valueOf(id.charAt(12))))
			return false;
		return true;
	};
}
