package com.eservice.core.beans;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUpload {
	
	private String type;
	private String name;
	private CommonsMultipartFile file;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

}
