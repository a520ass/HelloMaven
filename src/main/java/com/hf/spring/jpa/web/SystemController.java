package com.hf.spring.jpa.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping("/sys")
@Controller
public class SystemController {
	Logger log=LoggerFactory.getLogger(getClass());
	
	@RequiresUser
	@RequestMapping(value="/sys/f")
	public String index(){
		//log.info("跳转进入首页");
		return "sys/index";
	}
	
	@RequestMapping(value="/sys/login",method=RequestMethod.GET)
	public String login1(){
		if(SecurityUtils.getSubject().getPrincipal()!=null){
			return "redirect:/sys/f";
		}
		return "sys/login2";
	}
	
	//登陆失败跳转
	@RequestMapping(value="/sys/login",method=RequestMethod.POST)
	public String login2(){
		return "sys/login2";
	}
}
