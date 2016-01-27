package com.hf.spring.jpa.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.webupload.WebUploadUtils;
import com.hf.spring.jpa.common.mapper.JsonMapper;
import com.hf.spring.jpa.service.DepartmentService;
import com.hf.spring.jpa.service.EmployeeService;
import com.hf.spring.jpa.service.UserService;


@RequestMapping(value="/test")
@Controller
public class TestController {
	Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/test")
	public String test(Map<String,Object> map){
		map.put("department", departmentService.findDepartmentById(124));
		log.info("department put");
		return "success";
	}
	
	@RequestMapping("/testView")
	public String testView(){
		System.out.println("testView");
		return "success";
	}
	
	@RequestMapping("/testRedirect")
	public String testRedirect(){
		System.out.println("testRedirect");
		return "redirect:success";
	}
	
	@RequiresPermissions("sys:edit")
	@RequestMapping("/success")
	public String success(){
		return "success";
	}
	
	
	@RequestMapping(value="/testupload",method=RequestMethod.GET)
	public String testOssWebUpload(HttpServletRequest request,
			HttpServletResponse response){
		return "ossupload";
	}
	
	@RequestMapping("downloadfile")
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		byte[] b = new byte[1024];
		int n = 0;
		try {
			inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/note.txt");
			outputStream = response.getOutputStream();
			response.setContentType("application/force-download");
			String filename = "test.txt";
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8"));
			while ((n = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, n);
			}
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/testjson",method=RequestMethod.GET)
	public String testJson(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		/*response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(employeeService.findEmployees());
		response.getWriter().flush();
		response.getWriter().close();*/
		return JsonMapper.toJsonString(employeeService.findEmployees());
	}
	
	@ResponseBody
	@RequestMapping(value="/osswebupload",method=RequestMethod.GET)
	public String ossWebUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Map<String,String> postpolicy=WebUploadUtils.getPostPolicy();
		return JsonMapper.toJsonString(postpolicy);
	}
	
	@ResponseBody
	@RequestMapping(value="/testStringJson",method=RequestMethod.POST)
	public String testStringJson(){
		return "{\"success\":true,\"id\":\"1\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="/testHttpMessageConverter",method=RequestMethod.POST)
	public String testHttpMessageConverter(@RequestBody String bodydata){
		System.out.println(bodydata);
		return "helloworld!"+new Date();
	}
	
	//������������
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		byte[] body=null;
		ServletContext servletContext=session.getServletContext();
		InputStream in=servletContext.getResourceAsStream("index.jsp");
		body=new byte[in.available()];
		in.read(body);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=new jsp.txt");
		HttpStatus statusCode=HttpStatus.OK;
		ResponseEntity<byte[]> response =new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
		
	}
	
	//�ϴ��ļ�
	@RequestMapping(value="/testFileUpload",method=RequestMethod.POST)
	public String testFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("desc") String desc){
		try {
			InputStream in=file.getInputStream();
			FileOutputStream fout=new FileOutputStream("files/"+file.getOriginalFilename());
			fout.flush();
			fout.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(file.getContentType());
		return "success";
	}
	
	/*US
	Username
	CH
	�û���*/
	@RequestMapping(value="/i18n")
	public String i18n(Locale locale){
		System.out.println(locale.getCountry());
		String val=messageSource.getMessage("i18n.username", null, locale);
		System.out.println(val);
		return "success";
	}
	
	@RequestMapping(value="/i18n1")
	public String i18n1(){
		System.out.println("------------------------");
		return "redirect:success";
	}
	
	@RequestMapping(value="/testcache")
	public String cache(String username){
		username=null;
		System.out.println(userService.findUserByUsername(username).toString());
		return "redirect:success";
	}
}
