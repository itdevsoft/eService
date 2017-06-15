package com.eservice.core.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class NestedCategoryItemCompoundKey implements Serializable {

	private Item  item;

	private NestedCategory nestedCategory;

	public void setItem(Item item) {
		this.item = item;
	}

	@ManyToOne
	public Item getItem() {
		return item;
	}

	public void setNestedCategory(NestedCategory nestedCategory) {
		this.nestedCategory = nestedCategory;
	}

	@ManyToOne
	public NestedCategory getNestedCategory() {
		return nestedCategory;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        NestedCategoryItemCompoundKey that = (NestedCategoryItemCompoundKey) o;
 
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (nestedCategory != null ? !nestedCategory.equals(that.nestedCategory) : that.nestedCategory != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (item != null ? item.hashCode() : 0);
        result = 31 * result + (nestedCategory != null ? nestedCategory.hashCode() : 0);
        return result;
    }
	
}
