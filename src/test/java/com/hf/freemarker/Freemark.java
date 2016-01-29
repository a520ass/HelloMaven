package com.hf.freemarker;

import java.text.SimpleDateFormat;
import java.util.Date;

import freemarker.template.Configuration;

public class Freemark {
	
	public Freemark(String templatePath) {
		configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        configuration.setClassForTemplateLoading(this.getClass(),templatePath);
	}

	public static void main(String[] args) {
		Freemark freemark=new Freemark("template");
		freemark.setTemplateName("wordTemplate.ftl");  
        freemark.setFileName("doc_"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date())+".doc");  
        freemark.setFilePath("bin\\doc\\");  
        //生成word  
        freemark.createWord();  
	}
	
	private void createWord() {
		// TODO Auto-generated method stub
		
	}

	/** 
     * freemark模板配置 
     */  
    private Configuration configuration;  
    /** 
     * freemark模板的名字 
     */  
    private String templateName;  
    /** 
     * 生成文件名 
     */  
    private String fileName;  
    /** 
     * 生成文件路径 
     */  
    private String filePath;
    
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
    
    
}
