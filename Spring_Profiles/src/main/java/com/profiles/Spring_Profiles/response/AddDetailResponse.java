package com.profiles.Spring_Profiles.response;

public class AddDetailResponse {

	private Integer responseCode;

	private String responseMessage;

	public AddDetailResponse() {
		super();
	}

	public AddDetailResponse(Integer responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
