package com.example.demo.security;

import java.util.Collection;

import com.example.demo.model.User;

public class UserDetailsSerice implements UserDetails {
	
	User user;
	public UserDetailsSerice(User user)
	{
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
