package com.eservice.core.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="CATEGORY",uniqueConstraints={@UniqueConstraint(columnNames={"CATEGORY_ID"})})
@SequenceGenerator(name="category_sq", sequenceName="category_sq")
public class Category  implements Serializable{
	private long id;
	private String name;
	private int diffFactor;
	private String imagePath;
	private String imageExt;
	
	private CommonsMultipartFile fileData;
	
	@Id
	@Column(name="CATEGORY_ID", unique=true, nullable=false)
	@GeneratedValue(generator="category_sq",strategy=GenerationType.SEQUENCE)
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
	public void setDiffFactor(int diffFactor) {
		this.diffFactor = diffFactor;
	}
	@Column(name="DIFF_FACTOR")
	public int getDiffFactor() {
		return diffFactor;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Column(name="IMAGE_PATH")
	public String getImagePath() {
		return imagePath;
	}
	public void setImageExt(String imageExt) {
		this.imageExt = imageExt;
	}
	@Transient
	public String getImageExt() {
		return getImagePath()!=null?getImagePath().substring(getImagePath().lastIndexOf('.')+1):"";
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	@Transient
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	
}
