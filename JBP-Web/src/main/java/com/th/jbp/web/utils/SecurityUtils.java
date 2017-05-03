package com.th.jbp.web.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.th.jbp.jpa.entity.UserM;
import com.th.jbp.security.WebUserDetails;

public class SecurityUtils {

	private static final Logger logger = Logger.getLogger(SecurityUtils.class);
	public static final String DEFAULT_PASSWORD = "password";

	private SecurityUtils() {
	}

	public static String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public static UserM getWebUserDetails() {
		WebUserDetails webUserDetails = (WebUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return webUserDetails.getUser();
	}

	public static String generatePassword(String password) {
		return generatePassword(password, true);
	}

	public static String generatePassword(String password, boolean isUpperCase) {
		String md5 = null;
		if (StringUtils.isEmpty(password)) {
			throw new BadCredentialsException("Password is invalid.");
//			throw new NullPointerException();
		}
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes(), 0, password.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.toString(), e);
		}
		return isUpperCase ? md5.toUpperCase() : md5;
	}

	public static String randomPassword(int length) {
		String password = null;
		password = RandomStringUtils.randomAlphanumeric(length);
		return password;
	}

	public static String getOTP(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public static String getReferentCode(int length) {
		String k = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return RandomStringUtils.random(length, String.format("%s%s", k, k.toLowerCase()));

	}

	public static void main(String[] args) {
		System.out.println(generatePassword("password"));
	}
}
