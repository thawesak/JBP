package com.th.jbp.security;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

import com.th.jbp.web.utils.SecurityUtils;

public class CustomPasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
	private final static Logger logger = Logger.getLogger(CustomPasswordEncoder.class);

	@Override
	public String encode(CharSequence rawPassword) {
		logger.debug(MessageFormat.format("encodePassword {0}", rawPassword));
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		logger.debug(MessageFormat.format("isPasswordValid {0},{1},{2}", rawPassword, encodedPassword,
				(SecurityUtils.generatePassword(String.valueOf(rawPassword))).equals(encodedPassword)));
		if (encodedPassword == null) {
			return false;
		}
		return (encodedPassword).equals(SecurityUtils.generatePassword(String.valueOf(rawPassword))) ? true : false;
	}

}
