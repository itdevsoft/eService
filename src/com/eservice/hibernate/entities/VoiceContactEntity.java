package com.eservice.hibernate.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.eservice.core.beans.VoiceContact;




@Entity
@Table(name="VOICECONTACT",uniqueConstraints = {@UniqueConstraint(columnNames={"VOICECONTACTID","CONTACTTYPE"})})
@SequenceGenerator(name="voicecontact_sq", sequenceName="voicecontact_sq")
public class VoiceContactEntity extends VoiceContact implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Override
	@Id
	@Column(name="VOICECONTACTID", unique = true, nullable = false)
	@GeneratedValue(generator="voicecontact_sq",strategy=GenerationType.SEQUENCE)
	public long getId() {
		return super.getId();
	}

	@Override
	@Column(name="CONTACTNUMBER",nullable = false)
	public String getContactNumber() {
		return super.getContactNumber();
	}

	@Override
	@Column(name="PRIORITYLEVEL")
	public int getPriorityLevel() {
		return super.getPriorityLevel();
	}

	@Override
	@Column(name="CONTACTTYPE",nullable=false)
	public String getVoiceContactType() {
		return super.getVoiceContactType();
	}

	@Override
	public void setId(long id) {
		super.setId(id);
	}

	@Override
	public void setContactNumber(String contactNumber) {
		super.setContactNumber(contactNumber);
	}

	@Override
	public void setPriorityLevel(int priorityLevel) {
		super.setPriorityLevel(priorityLevel);
	}

	@Override
	public void setVoiceContactType(String voiceContactType) {
		super.setVoiceContactType(voiceContactType);
	}

	

}
