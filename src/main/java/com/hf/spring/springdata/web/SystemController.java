package com.hf.spring.springdata.web;

import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/sys")
@Controller
public class SystemController {
	private static final Logger log=LoggerFactory.getLogger(SystemController.class);
	
	@RequestMapping(value="/f")
	public String index(){
		//log.info("跳转进入首页");
		return "sys/index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginGet(Model model,HttpServletRequest request){
		Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
		String status = null;
		for (Entry<String, String[]> entry : entrySet) {
			if("logout".equals(entry.getKey())){
				log.info("用户登出系统");
				status="你已经登出系统、请重新登陆！";
			}
			if("invalidsession".equals(entry.getKey())){
				log.info("无效的session");
				status="无效的session、请重新登陆！";
			}
			if("authenticationfailure".equals(entry.getKey())){
				log.info("认证失败");
				status="认证失败、请重新登陆！";
			}
		}
		model.addAttribute("status", status);
		return "sys/login";
	}
	
	//登陆失败跳转
	/*@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login2(){
		
		return "sys/login2";
	}*/
}
