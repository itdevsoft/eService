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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Entity
@Table(name="SALE_ITEM",uniqueConstraints = {@UniqueConstraint(columnNames={"ITEM_ID"})})
@SequenceGenerator(name="item_sq", sequenceName="item_sq")
public class SaleItem implements Serializable{
	
	private long id;
	private String name;
	private String description;
	private boolean enabled;
	private String imagePath;
	private float basePrice;
	private String priceFor;
	private String imageExt;
	private List<NestedCategorySaleItem> nestedCategorySaleItem = new LinkedList<NestedCategorySaleItem>();
	
	private CommonsMultipartFile fileData;
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
	@Column(name="display_name",nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="enabled")
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Column(name="image_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	@Column(name="base_price")
	public float getBasePrice() {
		return basePrice;
	}
	
	public void setPriceFor(String priceFor) {
		this.priceFor = priceFor;
	}
	@Column(name="price_for")
	public String getPriceFor() {
		return priceFor;
	}
	public void setNestedCategorySet(Set<NestedCategory> nestedCategorySet) {
		this.nestedCategorySet =nestedCategorySet;
	}
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity=NestedCategory.class)
	@JoinTable(name = "nested_category_sale_item", joinColumns = { @JoinColumn(name = "ITEM_ID") }, inverseJoinColumns = { @JoinColumn(name = "NESTED_CATEGORY_ID") })
	public Set<NestedCategory> getNestedCategorySet() {
		return nestedCategorySet;
	}
	
	public void setNestedCategorySaleItem(List<NestedCategorySaleItem> nestedCategorySaleItem) {
		this.nestedCategorySaleItem = nestedCategorySaleItem;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "compoundKey.saleItem")
	public List<NestedCategorySaleItem> getNestedCategorySaleItem() {
		return nestedCategorySaleItem;
	}
	

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	@Transient
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
	@Transient
	public boolean isAssigned() {
		return assigned;
	}
	public void setImageExt(String imageExt) {
		this.imageExt = imageExt;
	}
	@Transient
	public String getImageExt() {
		return getImagePath()!=null?getImagePath().substring(getImagePath().lastIndexOf('.')+1):"";
	}
	
	
}
