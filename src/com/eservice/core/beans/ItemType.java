package com.eservice.core.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="ITEM_TYPE",uniqueConstraints = {@UniqueConstraint(columnNames={"ITEM_TYPE_ID"})})
@SequenceGenerator(name="item_type_sq", sequenceName="item_type_sq")
public class ItemType implements Serializable{
	
	private long id;
	private String name;
	private boolean enabled;
	
	public void setId(long id) {
		this.id = id;
	}
	@Id
	@Column(name="item_type_id", unique=true, nullable=false)
	@GeneratedValue(generator="item_type_sq",strategy=GenerationType.SEQUENCE)
	public long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="NAME",nullable=false)
	public String getName() {
		return name;
	}

	@Column(name="ENABLED")
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getId());
	}
}
