package com.futura.Purple.Auth.payload.response;


import java.util.List;

import com.futura.Purple.Entity.Permissions;
import com.futura.Purple.Entity.UserGroup;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private Long id;
	private String username;
	private String email;
	private UserGroup userGroup;
	private List<Permissions> specialAccess;



	public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email,UserGroup userGroup,List<Permissions> specialAccess) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.userGroup=userGroup;
		this.specialAccess = specialAccess;

	}

	public String getAccessToken() {
		return token;
	}


	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setRole(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public List<Permissions> getSpecialAccess() {
		return specialAccess;
	}

	public void setSpecialAccess(List<Permissions> specialAccess) {
		this.specialAccess = specialAccess;
	}

	public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
