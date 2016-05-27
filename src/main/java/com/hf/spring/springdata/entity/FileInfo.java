package com.hf.spring.springdata.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="FILE_INFO")
@Entity
public class FileInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String fileName;//文件存储名
	private String fileOriginalName;//文件原始名
	private Long fileTs;
	private Long fileSize;
	private String fileMD5;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public Long getFileTs() {
		return fileTs;
	}
	public void setFileTs(Long fileTs) {
		this.fileTs = fileTs;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileMD5() {
		return fileMD5;
	}
	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}
	public FileInfo() {
		super();
	}
	public FileInfo(String fileName, String fileOriginalName, Long fileTs,
			Long fileSize, String fileMD5) {
		super();
		this.fileName = fileName;
		this.fileOriginalName = fileOriginalName;
		this.fileTs = fileTs;
		this.fileSize = fileSize;
		this.fileMD5 = fileMD5;
	}

}
