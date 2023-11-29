package com.futura.Purple.Auth.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.futura.Purple.Auth.models.User;
import com.futura.Purple.Entity.Permissions;
import com.futura.Purple.Entity.UserGroup;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;
	
	@JsonIgnore
	private String password;

	private UserGroup userGroup;

	private List<Permissions> specialAccess;

	public UserDetailsImpl(Long id, String username, String email, String password,UserGroup userGroup,List<Permissions> specialAccess) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userGroup=userGroup;
		this.specialAccess = specialAccess ;
	} 

	public static UserDetailsImpl build(User user) {

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),
				user.getUserGroup(),
				user.getSpecialAccess()
				);
	}


	public Long getId() {
		return id;
	}

	

	public UserGroup getUserGroup() {
		return userGroup;
	}

	

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}




	public List<Permissions> getSpecialAccess() {
		return specialAccess;
	}

	public void setSpecialAccess(List<Permissions> specialAccess) {
		this.specialAccess = specialAccess;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
