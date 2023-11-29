package com.futura.Purple.Auth.payload.request;

public class LogOutRequest {
  private Long userId;

  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public Long getUserId() {
    return this.userId;
  }
}
