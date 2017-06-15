package com.eservice.hibernate.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.eservice.core.beans.User;

@Entity
@Table(name="SECURITYUSER",uniqueConstraints = {@UniqueConstraint(columnNames={"USERID","USERNAME"})})
@SequenceGenerator(name="securityuser_sq", sequenceName="securityuser_sq")
	public class UserEntity extends User implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Set<VoiceContactEntity> voiceContactDetails;
	
	Set<LocationContactEntity> locationContactDetails;
	
	@Column(name="GENDER")
	@Override
	public char getGender() {
		
		return super.getGender();
	}

	@Id
	@Column(name="USERID", unique = true, nullable = false)
	@GeneratedValue(generator="securityuser_sq",strategy=GenerationType.SEQUENCE)
	@Override
	public long getId() {
		return super.getId();
	}

	@Column(name="LASTNAME",nullable = false)
	@Override
	public String getLastName() {
		
		return super.getLastName();
	}

	@Column(name="OTHERNAMES",nullable = false)
	@Override
	public String getOtherNames() {
		
		return super.getOtherNames();
	}

	@Column(name="PASSWORD")
	@Override
	public String getPassword() {
		
		return super.getPassword();
	}

	@Column(name="TITLE")
	@Override
	public String getTitle() {
		
		return super.getTitle();
	}

	@Column(name="USERNAME",unique = true, nullable = false)
	@Override
	public String getUserName() {
		
		return super.getUserName();
	}

	
	@Column(name="ENABLED")
	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}

	@Column(name="EMAIL")
	@Override
	public String getEmail() {
		return super.getEmail();
	}

	
	@Column(name="CREATEDON")
	@Override
	public Date getCreatedOn() {
		return super.getCreatedOn();
	}

	@Column(name="LASTUPDATEDON")
	@Override
	public Date getLastUpdatedOn() {
		return super.getLastUpdatedOn();
	}

	@OneToMany(cascade = CascadeType.ALL,targetEntity=VoiceContactEntity.class)
	@JoinTable(name = "USER_VOICECONTACT", joinColumns = { @JoinColumn(name = "USERID") }, inverseJoinColumns = { @JoinColumn(name = "VOICECONTACTID") })
	public Set<VoiceContactEntity> getVoiceContactDetails() {
		return new HashSet(super.getVoiceContactDetailList());
	}

	public void setVoiceContactDetails(Set<VoiceContactEntity> voiceContactDetails) {
		// TODO Auto-generated method stub
		super.setVoiceContactDetailList(new ArrayList<VoiceContactEntity>(voiceContactDetails));
	}
	
	@OneToMany(cascade = CascadeType.ALL,targetEntity=LocationContactEntity.class)
	@JoinTable(name = "USER_LOCATIONCONTACT", joinColumns = { @JoinColumn(name = "USERID") }, inverseJoinColumns = { @JoinColumn(name = "LOCATIONCONTACTID") })
	public Set<LocationContactEntity> getLocationContactDetails() {
		return new HashSet(super.getLocationContactDetailList());
	}

	public void setLocationContactDetails(Set<LocationContactEntity> locationContactDetails) {
		// TODO Auto-generated method stub
		super.setLocationContactDetailList(new ArrayList<LocationContactEntity>(locationContactDetails));
	}

	@Override
	public void setCreatedOn(Date createdOn) {
		super.setCreatedOn(createdOn);
	}

	@Override
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		super.setLastUpdatedOn(lastUpdatedOn);
	}

	@Override
	public void setEnabled(boolean enabled) {
		
		super.setEnabled(enabled);
	}

	@Override
	public void setGender(char gender) {
		
		super.setGender(gender);
	}

	@Override
	public void setId(long id) {
		
		super.setId(id);
	}

	@Override
	public void setLastName(String lastName) {
		
		super.setLastName(lastName);
	}

	
	@Override
	public void setOtherNames(String otherNames) {
		
		super.setOtherNames(otherNames);
	}

	@Override
	public void setPassword(String password) {
		
		super.setPassword(password);
	}

	@Override
	public void setTitle(String title) {
		
		super.setTitle(title);
	}

	@Override
	public void setUserName(String userName) {
		
		super.setUserName(userName);
	}

	@Override
	public void setEmail(String email) {
		super.setEmail(email);
	}

	
	
}
