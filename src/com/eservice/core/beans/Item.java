package com.eservice.core.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="ITEM",uniqueConstraints = {@UniqueConstraint(columnNames={"ITEM_ID"})})
@SequenceGenerator(name="item_sq", sequenceName="item_sq")
public class Item implements Serializable{
	
	private long id;
	private String name;
	private ItemType itemType;
	private boolean enabled;
	private List<NestedCategoryItem> nestedCategoryItem = new LinkedList<NestedCategoryItem>();
	
	private Set<NestedCategory> nestedCategorySet;
	private boolean assigned;
	
	@Id
	@Column(name="item_id", unique=true, nullable=false)
	@GeneratedValue(generator="item_sq",strategy=GenerationType.SEQUENCE)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name="NAME",nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ITEM_TYPE_ID")
	public ItemType getitemType() {
		return itemType;
	}
	public void setitemType(ItemType itemType) {
		this.itemType = itemType;
	}
	@Column(name="ENABLED")
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setNestedCategorySet(Set<NestedCategory> nestedCategorySet) {
		this.nestedCategorySet =nestedCategorySet;
	}
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity=NestedCategory.class)
	@JoinTable(name = "NESTED_CATEGORY_ITEM", joinColumns = { @JoinColumn(name = "ITEM_ID") }, inverseJoinColumns = { @JoinColumn(name = "NESTED_CATEGORY_ID") })
	public Set<NestedCategory> getNestedCategorySet() {
		return nestedCategorySet;
	}
	
	public void setNestedCategoryItem(List<NestedCategoryItem> nestedCategoryItem) {
		this.nestedCategoryItem = nestedCategoryItem;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "compoundKey.item")
	public List<NestedCategoryItem> getNestedCategoryItem() {
		return nestedCategoryItem;
	}
	
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
	@Transient
	public boolean isAssigned() {
		return assigned;
	}
	
}
