package com.profiles.Spring_Profiles.model;

public class UserDetailDTO {

	private String name;

	private String email;

	private String phone;

	private String linkedIn;

	public UserDetailDTO() {
		super();
	}

	public UserDetailDTO(String name, String email, String phone, String linkedIn) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.linkedIn = linkedIn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}
