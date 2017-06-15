package com.eservice.core.beans;

import java.util.List;

public class ContactDetail {
	private String contactType;
	private LocationContact locationContact;
	private List<VoiceContact> voiceContacts;
	private WebContact webContact;
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public LocationContact getLocationContact() {
		return locationContact;
	}
	public void setLocationContact(LocationContact locationContact) {
		this.locationContact = locationContact;
	}
	public List<VoiceContact> getVoiceContacts() {
		return voiceContacts;
	}
	public void setVoiceContacts(List<VoiceContact> voiceContacts) {
		this.voiceContacts = voiceContacts;
	}
	public WebContact getWebContact() {
		return webContact;
	}
	public void setWebContact(WebContact webContact) {
		this.webContact = webContact;
	}
	
}
