package com.th.jbp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.th.jbp.jpa.entity.UserM;

public class WebUserDetails implements UserDetails {

	private static final long serialVersionUID = -3781930066180957676L;
	private UserM user;
	private String username;
	private String password;
	private List<WebRole> roles;

	public WebUserDetails(String username, String password, UserM user, List<WebRole> roles) {
		this.username = username;
		this.password = password;
		this.user = user;
		this.roles = roles;
	}

	public List<WebRole> getRoles() {
		return roles;
	}

	public void setRoles(List<WebRole> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public Collection<GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		for (WebRole role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return grantedAuthorities;
	}

	public UserM getUser() {
		return user;
	}

	public void setUser(UserM user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
