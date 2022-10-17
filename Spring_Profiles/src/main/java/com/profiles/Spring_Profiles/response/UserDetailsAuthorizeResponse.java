package com.profiles.Spring_Profiles.response;

public class UserDetailsAuthorizeResponse {

	private Integer code;

	private String message;

	private String authToken;

	public UserDetailsAuthorizeResponse() {
		super();
	}

	public UserDetailsAuthorizeResponse(Integer code, String message, String authToken) {
		super();
		this.code = code;
		this.message = message;
		this.authToken = authToken;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

}
