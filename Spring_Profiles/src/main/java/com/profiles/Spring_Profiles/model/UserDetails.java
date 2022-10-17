package com.profiles.Spring_Profiles.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer userId;

//	@Pattern(regexp="[a-z]{6,12}", message = "Username must be between 6 to 12 characters. Must only contain lowercase characters.")
	private String name;

	private String email;

	private String phone;

	private String linkedIn;

//	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	LocalDateTime createdAt;

//	private String createdAt= formatter.format(date);

	public UserDetails() {
		super();
	}

	public UserDetails(Integer id, Integer userId, String name, String email, String phone, String linkedIn,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.linkedIn = linkedIn;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

//	public void setLocalDateAndTime(String localDateAndTime) {
//		this.localDateAndTime=localDateAndTime;
//	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
