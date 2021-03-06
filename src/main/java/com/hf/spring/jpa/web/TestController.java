package com.hf.spring.jpa.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.webupload.WebUploadUtils;
import com.hf.reflection.Reflections;
import com.hf.spring.jpa.entity.Department;
import com.hf.spring.jpa.entity.Employee;
import com.hf.spring.jpa.entity.User;
import com.hf.spring.jpa.service.DepartmentService;
import com.hf.spring.jpa.service.EmployeeService;
import com.hf.spring.jpa.service.UserService;


@RequestMapping(value="/test")
@Controller
public class TestController {
	private final Logger log=LoggerFactory.getLogger(getClass());
	private static final int BUFFER_SIZE = 100 * 1024;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	@Autowired
	private SessionDAO sessionDAO;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@RequestMapping("/listsession")
	@ResponseBody
    public Collection<Session> list(Model model) {  
        Collection<Session> sessions =  sessionDAO.getActiveSessions();  
        model.addAttribute("sessions", sessions);  
        model.addAttribute("sesessionCount", sessions.size());  
        return sessions;
    }
	
	@RequestMapping("/redis")
	@ResponseBody
    public Object redis(Model model) {  
		redisCacheManager.getCache("diycache").put("re", "hefen");
		redisTemplate.opsForValue().set("werew", "laa我的贝贝all");
        Object object = redisTemplate.opsForValue().get("werew");
        return object;
    }
	
	@RequestMapping("/test")
	public String test(Map<String,Object> map){
		Department department1 = departmentService.findDepartmentById(124);
		map.put("department", department1);
		
		try {
			Field field=Reflections.getAccessibleField(department1, "employees");
			Object object = field.get(department1);
			Hibernate.initialize(object);
			Set<Employee> value = (Set<Employee>) Reflections.getFieldValue(object, "set");
			System.out.println(object.getClass().getCanonicalName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/*String name = department1.getEmployees().getClass().getName();
		Hibernate.initialize(department1.getEmployees());
		String name1 = department1.getEmployees().getClass().getName();*/
		
		Employee findEmplyeeById = employeeService.findEmplyeeById(11);
		try {
			/*Field field = findEmplyeeById.getClass().getDeclaredField("department");
			field.setAccessible(true);*/
			Field field=Reflections.getAccessibleField(findEmplyeeById, "department");
			Object object = field.get(findEmplyeeById);
			Hibernate.initialize(object);
			/*Field handlerField = object.getClass().getDeclaredField("handler");
			handlerField.setAccessible(true);*/
			Field handlerField=Reflections.getAccessibleField(object, "handler");
			Object proxyobject = handlerField.get(object);
			Department department = (Department) Reflections.getFieldValue(proxyobject, "target");
			System.out.println(proxyobject.getClass().getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	
	@RequestMapping("/testfreemarker")
	public String testfreemarker(HttpServletRequest request,Model model){
		String contextPath = request.getContextPath()+"/static";
		model.addAttribute("ctxStatic", contextPath);
		model.addAttribute("username", "何锋");
		return "f1";
	}
	
	
	@RequestMapping(value="/testupload",method=RequestMethod.GET)
	public String testOssWebUpload(HttpServletRequest request,
			HttpServletResponse response){
		return "ossupload";
	}
	
	@ResponseBody
	@RequestMapping(value="/osswebupload",method=RequestMethod.GET)
	public Map<String,String> ossWebUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Map<String,String> postpolicy=WebUploadUtils.getPostPolicy();
		postpolicy.put("dir", "user/1.2/3.4.5.");
		//user/不变，后面加参数。构造成最终上传上去的路径及文件名
		return postpolicy;
	}
	
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
	            //FileInputStream openInputStream = FileUtils.openInputStream(destFile);
	            //byte[] byteArray = IOUtils.toByteArray(openInputStream);
	            String md5 = DigestUtils.md5Hex(FileUtils.openInputStream(destFile));//使用一次后。后面使用需要重新得到输入流
	            String sha1 = DigestUtils.sha1Hex(FileUtils.openInputStream(destFile));
	            //int length = IOUtils.toByteArray(openInputStream).length;
	            System.err.println("md5:"+md5+", sha1:"+sha1+",  size:"+destFile.length());
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
	
	@RequestMapping("/downloadfile")
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		byte[] b = new byte[1024];
		int n = 0;
		try {
			//TestController.class.getClassLoader()
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
	@RequestMapping(value="/testjson",method=RequestMethod.POST)
	public List<Employee> testJson(@RequestBody List<User> user, HttpServletRequest request,
			HttpServletResponse response){
		log.warn(user.toString());
		List<Employee> employees = employeeService.findEmployees();
		return employees;
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
			int len = 0;
			byte[] buffer = new byte[BUFFER_SIZE];			
			while ((len = in.read(buffer)) != -1) {
				fout.write(buffer, 0, len);
			}
			fout.flush();
			fout.close();
			in.close();
		} catch (IOException e) {
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
