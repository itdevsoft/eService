package com.eservice.core.beans;

import java.sql.Date;
import java.util.List;

public class User {
	private long id;
	private String userName;
	private String password;
	private String otherNames;
	private String lastName;
	private String title;
	private char gender;
	private boolean enabled;
	private Date createdOn;
	private Date lastUpdatedOn;
	private List<? extends VoiceContact> voiceContactDetailList;
	private List<? extends LocationContact> locationContactDetailList;
	private String email;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setVoiceContactDetailList(List<? extends VoiceContact> voiceContactDetailList) {
		this.voiceContactDetailList = voiceContactDetailList;
	}

	public List<? extends VoiceContact> getVoiceContactDetailList() {
		return voiceContactDetailList;
	}

	public void setLocationContactDetailList(
			List<? extends LocationContact> locationContactDetailList) {
		this.locationContactDetailList = locationContactDetailList;
	}

	public List<? extends LocationContact> getLocationContactDetailList() {
		return locationContactDetailList;
	}

	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("userName=" + getUserName()+" , ");
		sb.append("password=" + getPassword()+" , ");
		sb.append("otherNames=" + getOtherNames()+" , ");
		sb.append("lastName=" + getLastName()+" , ");
		sb.append("title=" + getTitle()+" , ");
		sb.append("gender=" + getGender()+" , ");
		sb.append("enabled=" + isEnabled()+" , ");
		sb.append("email=" + getEmail());
		sb.append("voiceContactDetails=" + getVoiceContactDetailList());
		sb.append("locationContactDetails=" + getLocationContactDetailList());
		return sb.toString();
	}
}
