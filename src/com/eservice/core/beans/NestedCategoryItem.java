package com.eservice.core.beans;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "NESTED_CATEGORY_ITEM")
@AssociationOverrides( {
		@AssociationOverride(name = "compoundKey.item", joinColumns = @JoinColumn(name = "ITEM_ID")),
		@AssociationOverride(name = "compoundKey.nestedCategory", joinColumns = @JoinColumn(name = "NESTED_CATEGORY_ID")) })
public class NestedCategoryItem implements Serializable{

	private NestedCategoryItemCompoundKey compoundKey = new NestedCategoryItemCompoundKey();

	public void setCompoundKey(NestedCategoryItemCompoundKey compoundKey) {
		this.compoundKey = compoundKey;
	}

	private boolean assigned;
	
	@EmbeddedId
	public NestedCategoryItemCompoundKey getCompoundKey() {
		return compoundKey;
	}

	@Transient
	public Item getItem(){
		return getCompoundKey().getItem();
	}
	
	public void setItem(Item item){
		getCompoundKey().setItem(item);
	}
	
	@Transient
	public NestedCategory getNestedCategory(){
		return getCompoundKey().getNestedCategory();
	}
	
	public void setNestedCategory(NestedCategory nestedCategory){
		getCompoundKey().setNestedCategory(nestedCategory);
	}
	
	
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
	
	@Column(name="ACTIVE")
	public boolean isAssigned() {
		return assigned;
	}

	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        NestedCategoryItem that = (NestedCategoryItem) o;
 
        if (getCompoundKey() != null ? !getCompoundKey().equals(that.getCompoundKey()) : that.getCompoundKey() != null) return false;
 
        return true;
    }
 
    public int hashCode() {
        return (getCompoundKey() != null ? getCompoundKey().hashCode() : 0);
    }
	
}