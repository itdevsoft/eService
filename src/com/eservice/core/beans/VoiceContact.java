package com.eservice.core.beans;

public class VoiceContact {
	private long id;
	private String voiceContactType;
	private String contactNumber;
	private int priorityLevel;
	public String getVoiceContactType() {
		return voiceContactType;
	}
	public void setVoiceContactType(String voiceContactType) {
		this.voiceContactType = voiceContactType;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public int getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("voiceContactType=" + getVoiceContactType()+" , ");
		sb.append("contactNumber=" + getContactNumber()+" , ");
		sb.append("priorityLevel=" + getPriorityLevel());
		return sb.toString();
	}
}
