package com.eservice.core.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class NestedCategorySaleItemCompoundKey implements Serializable {

	private SaleItem saleItem;

	private NestedCategory nestedCategory;

	public void setSaleItem(SaleItem saleItem) {
		this.saleItem = saleItem;
	}

	@ManyToOne
	public SaleItem getSaleItem() {
		return saleItem;
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
 
        NestedCategorySaleItemCompoundKey that = (NestedCategorySaleItemCompoundKey) o;
 
        if (saleItem != null ? !saleItem.equals(that.saleItem) : that.saleItem != null) return false;
        if (nestedCategory != null ? !nestedCategory.equals(that.nestedCategory) : that.nestedCategory != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (saleItem != null ? saleItem.hashCode() : 0);
        result = 31 * result + (nestedCategory != null ? nestedCategory.hashCode() : 0);
        return result;
    }
	
}
