package com.hf.spring.springdata.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hf.spring.springdata.entity.FileInfo;
import com.hf.spring.springdata.service.FileInfoService;

@RequestMapping("/test")
@Controller
public class TestController {
	private static final Logger log=LoggerFactory.getLogger(TestController.class);
	private static final int BUFFER_SIZE = 100 * 1024;
	@Autowired
	private FileInfoService fileInfoService;
	
	@ResponseBody
	@RequestMapping(value="/testplupload",method=RequestMethod.POST)	
	public String plupload(@RequestParam MultipartFile file, HttpServletRequest request, HttpSession session) {
		try {
			String name = request.getParameter("name");
			Integer chunk = 0, chunks = 0;
			if(null != request.getParameter("chunk") && !request.getParameter("chunk").equals("")){
				chunk = Integer.valueOf(request.getParameter("chunk"));
			}
			if(null != request.getParameter("chunks") && !request.getParameter("chunks").equals("")){
				chunks = Integer.valueOf(request.getParameter("chunks"));
			}
			log.info("chunk:[" + chunk + "] chunks:[" + chunks + "]");
			//检查文件目录，不存在则创建
			String relativePath = "/plupload/files/";
			String realPath = session.getServletContext().getRealPath("");
			File folder = new File(realPath + relativePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			//目标文件 
			File destFile = new File(folder, name);
			//文件已存在删除旧文件（上传了同名的文件） 
	        if (chunk == 0 && destFile.exists()) {  
	        	destFile.delete();  
	        	destFile = new File(folder, name);
	        }
	        //合成文件
	        appendFile(file.getInputStream(), destFile);  
	        if (chunk == chunks - 1) {  
	            log.info("上传完成");
	            //byte[] byteArray = IOUtils.toByteArray(openInputStream);
	            String fileMD5 = DigestUtils.md5Hex(FileUtils.openInputStream(destFile));//使用一次后。后面使用需要重新得到输入流
	            //String sha1 = DigestUtils.sha1Hex(FileUtils.openInputStream(destFile));
	            long fileSize = destFile.length();
	            
	            FileInfo fileInfo=new FileInfo(destFile.getName(), file.getOriginalFilename(), System.currentTimeMillis(), fileSize, fileMD5);
	            fileInfoService.saveEntity(fileInfo);
	            //System.err.println("md5:"+md5+", sha1:"+sha1+",  size:"+destFile.length());
	        }else {
	        	log.info("还剩["+(chunks-1-chunk)+"]个块文件");
	        }
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		
		return "";
	}
	
	private void appendFile(InputStream in, File destFile) {
		OutputStream out = null;
		try {
			// plupload 配置了chunk的时候新上传的文件append到文件末尾
			if (destFile.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(destFile, true), BUFFER_SIZE); 
			} else {
				out = new BufferedOutputStream(new FileOutputStream(destFile),BUFFER_SIZE);
			}
			in = new BufferedInputStream(in, BUFFER_SIZE);
			
			int len = 0;
			byte[] buffer = new byte[BUFFER_SIZE];			
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {		
			try {
				if (null != in) {
					in.close();
				}
				if(null != out){
					out.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}
	
}
