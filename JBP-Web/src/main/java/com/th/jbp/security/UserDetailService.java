package com.th.jbp.security;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.backend.service.UserService;
import com.th.jbp.enums.Status;
import com.th.jbp.jpa.entity.RoleM;
import com.th.jbp.jpa.entity.UserM;



public class UserDetailService implements UserDetailsService {

	private final static Logger logger = Logger.getLogger(UserDetailService.class);

	@Autowired
	UserService userService = null;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername : " + username);
		List<WebRole> roles = new ArrayList<WebRole>();
		UserM user = null;
		// try {
		if(StringUtils.isEmpty(username)){
			logger.info("username is null.");
			throw new UsernameNotFoundException("Username is invalid.");
		}
		
		ObjectResult<UserM> objectResult = userService.findByUsername(username);
		user = objectResult.getObject();
		if (objectResult.getCode() == ObjectResult.SUCCESS) {
			Status status = Status.get(user.getStatus().getClassifierValueId());
			if (Status.ACTIVE.equals(status)) {
				user = objectResult.getObject();
				logger.info("Username : " + user.getUsername());
				logger.info("Password : " + user.getPassword());
				Set<RoleM> bmmRoles = user.getRoles();
				if (bmmRoles != null) {
					for (RoleM bmmRole : bmmRoles) {
						if (bmmRole.getRoleId() == 1)
							roles.add(new WebRole("MD"));
						else if (bmmRole.getRoleId() == 2)
							roles.add(new WebRole("ASSISTANT"));
						else if (bmmRole.getRoleId() == 3)
							roles.add(new WebRole("OFFICER1"));
						else if (bmmRole.getRoleId() == 4)
							roles.add(new WebRole("OFFICER2"));
						else if (bmmRole.getRoleId() == 5)
							roles.add(new WebRole("OFFICER3"));
						else if (bmmRole.getRoleId() == 6)
							roles.add(new WebRole("HR"));
					}
				}
				logger.info("roles : " + ToStringBuilder.reflectionToString(roles.get(0)));
				if (roles.isEmpty()) {
					throw new BadCredentialsException("Username '" + username + "' no permission in application.");
				}
			} else if (Status.INACTIVE.equals(status)) {
				user = objectResult.getObject();
				throw new BadCredentialsException(
						"Username '" + username + "' is disabled. please contract administrator.");
			} else if (Status.DELETED.equals(status)) {
				user = objectResult.getObject();
				throw new BadCredentialsException("Username '" + username + "' not found.");
			}

		} else if (objectResult.getCode() == ObjectResult.DATA_NOT_FOUND) {
			logger.info("User not found.");
			throw new BadCredentialsException("Username '" + username + "' not found.");
		} else {
			logger.info("UserModel is null.");
			throw new UsernameNotFoundException("Username '" + username + "' not found.");
		}

		/*
		 * } catch (Exception e) { logger.error(e.toString(), e); }
		 */

		user.setLastLoggedIn(new Timestamp(System.currentTimeMillis()));
		userService.update(user);
		return new WebUserDetails(username, user.getPassword(), user, roles);
	}

}
