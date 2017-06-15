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

import com.eservice.core.beans.LocationContact;

@Entity
@Table(name="LOCATIONCONTACT",uniqueConstraints = {@UniqueConstraint(columnNames={"LOCATIONCONTACTID"})})
@SequenceGenerator(name="locationcontact_sq", sequenceName="locationcontact_sq")
public class LocationContactEntity extends LocationContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Id
	@Column(name="LOCATIONCONTACTID", unique = true, nullable = false)
	@GeneratedValue(generator="locationcontact_sq",strategy=GenerationType.SEQUENCE)
	public long getId() {
		return super.getId();
	}

	@Override
	@Column(name="ADDRESSLINE1",nullable=false)
	public String getAddressLine1() {
		
		return super.getAddressLine1();
	}

	@Override
	@Column(name="ADDRESSLINE2")
	public String getAddressLine2() {
		
		return super.getAddressLine2();
	}

	@Override
	@Column(name="CITY")
	public String getCity() {
		
		return super.getCity();
	}

	@Override
	@Column(name="COUNTRYCODE")
	public String getCountryCode() {
		
		return super.getCountryCode();
	}

	@Override
	@Column(name="POSTCODE")
	public String getPostCode() {
		
		return super.getPostCode();
	}

	@Override
	@Column(name="PRIORIYLEVEL")
	public int getPrioriyLevel() {
		
		return super.getPrioriyLevel();
	}

	@Override
	@Column(name="STATECODE")
	public String getStateCode() {
		
		return super.getStateCode();
	}

	@Override
	public void setId(long id) {
		
		super.setId(id);
	}

	@Override
	public void setAddressLine1(String addressLine1) {
		
		super.setAddressLine1(addressLine1);
	}

	@Override
	public void setAddressLine2(String addressLine2) {
		
		super.setAddressLine2(addressLine2);
	}

	@Override
	public void setCity(String city) {
		
		super.setCity(city);
	}

	@Override
	public void setCountryCode(String countryCode) {
		
		super.setCountryCode(countryCode);
	}

	@Override
	public void setPostCode(String postCode) {
		
		super.setPostCode(postCode);
	}

	@Override
	public void setPrioriyLevel(int prioriyLevel) {
		
		super.setPrioriyLevel(prioriyLevel);
	}

	@Override
	public void setStateCode(String stateCode) {
		
		super.setStateCode(stateCode);
	}
	
	
	

}
