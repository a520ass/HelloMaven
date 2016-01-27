package com.baidu.ueditor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.aliyun.webupload.WebUploadUtils;
import com.qikemi.packages.alibaba.aliyun.oss.OSSClientFactory;
import com.qikemi.packages.utils.SystemUtil;

public class OssUpload extends HttpServlet {
	private static final long serialVersionUID = -4500613200275499491L;
	
	private static Logger logger = Logger.getLogger(OssUpload.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet");
		resp.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Map<String,String> postpolicy=WebUploadUtils.getPostPolicy();
		String jsonString = JSON.toJSONString(postpolicy);
		out.print(jsonString);
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// super.doPost(req, resp);
	}
}
