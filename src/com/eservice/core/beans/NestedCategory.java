package com.eservice.core.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
//@javax.persistence.NamedNativeQuery(name = "get_immediate_subordinates", query = "{ ? = call get_immediate_subordinates(:vCategoryId) }", resultClass = NestedCategory.class, hints = { @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") })
@Table(name="NESTED_CATEGORY",uniqueConstraints={@UniqueConstraint(columnNames={"NESTED_CATEGORY_ID"})})
@SequenceGenerator(name="nested_category_sq", sequenceName="nested_category_sq")
public class NestedCategory  implements Serializable{
	private long id;
	private Category category;
	private int left;
	private int right;
	private String type;
	private List<NestedCategoryItem> nestedCategoryItems = new LinkedList<NestedCategoryItem>();
	private List<SaleItem> saleItems  = new LinkedList<SaleItem>();
	private List<NestedCategorySaleItem> nestedCategorySaleItems  = new LinkedList<NestedCategorySaleItem>();
	
	private long parentId;
	private List<Item> items;
	private boolean selected;
	
	@Id
	@Column(name="NESTED_CATEGORY_ID", unique=true, nullable=false)
	@GeneratedValue(generator="nested_category_sq",strategy=GenerationType.SEQUENCE)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name="LFT")
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	@Column(name="RGT")
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="CATEGORY_ID")
	public Category getCategory() {
		return category;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="TYPE", nullable=false)
	public String getType() {
		return type;
	}
	
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	public void setNestedCategoryItems(List<NestedCategoryItem> nestedCategoryItems) {
		this.nestedCategoryItems = nestedCategoryItems;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "compoundKey.nestedCategory")
	public List<NestedCategoryItem> getNestedCategoryItems() {
		return nestedCategoryItems;
	}
	
	@Transient
	public long getParentId() {
		return parentId;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Transient
	public List<Item> getItems() {
		return items;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	@Transient
	public boolean isSelected() {
		return selected;
	}
	@Transient
	public List<SaleItem> getSaleItems() {
		return saleItems;
	}
	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}
	@Transient
	public List<NestedCategorySaleItem> getNestedCategorySaleItems() {
		return nestedCategorySaleItems;
	}
	public void setNestedCategorySaleItems(List<NestedCategorySaleItem> nestedCategorySaleItems) {
		this.nestedCategorySaleItems = nestedCategorySaleItems;
	}
	
}
