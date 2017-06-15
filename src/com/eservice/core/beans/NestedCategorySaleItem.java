package com.eservice.core.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "NESTED_CATEGORY_SALE_ITEM")
@AssociationOverrides( {
		@AssociationOverride(name = "compoundKey.saleItem", joinColumns = @JoinColumn(name = "ITEM_ID")),
		@AssociationOverride(name = "compoundKey.nestedCategory", joinColumns = @JoinColumn(name = "NESTED_CATEGORY_ID")) })
public class NestedCategorySaleItem implements Serializable{

	private NestedCategorySaleItemCompoundKey compoundKey = new NestedCategorySaleItemCompoundKey();

	public void setCompoundKey(NestedCategorySaleItemCompoundKey compoundKey) {
		this.compoundKey = compoundKey;
	}

	private String displayName;
	
	private String description;
	
	private float price;
	
	private boolean assigned;
	
	private List<NestedCategorySaleItem> nestedCategorySaleItem = new LinkedList<NestedCategorySaleItem>();
	
	
	@EmbeddedId
	public NestedCategorySaleItemCompoundKey getCompoundKey() {
		return compoundKey;
	}

	@Transient
	public SaleItem getSaleItem(){
		return getCompoundKey().getSaleItem();
	}
	
	public void setSaleItem(SaleItem saleItem){
		getCompoundKey().setSaleItem(saleItem);
	}
	
	@Transient
	public NestedCategory getNestedCategory(){
		return getCompoundKey().getNestedCategory();
	}
	
	public void setNestedCategory(NestedCategory nestedCategory){
		getCompoundKey().setNestedCategory(nestedCategory);
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(name="DISPLAY_NAME")
	public String getDisplayName() {
		return displayName;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="PRICE")
	public float getPrice() {
		return price;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
	
	@Column(name="ACTIVE")
	public boolean isAssigned() {
		return assigned;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Transient
	public List<NestedCategorySaleItem> getNestedCategorySaleItem() {
		return nestedCategorySaleItem;
	}

	public void setNestedCategorySaleItem(List<NestedCategorySaleItem> nestedCategorySaleItem) {
		this.nestedCategorySaleItem = nestedCategorySaleItem;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        NestedCategorySaleItem that = (NestedCategorySaleItem) o;
 
        if (getCompoundKey() != null ? !getCompoundKey().equals(that.getCompoundKey()) : that.getCompoundKey() != null) return false;
 
        return true;
    }
 
    public int hashCode() {
        return (getCompoundKey() != null ? getCompoundKey().hashCode() : 0);
    }
	
}