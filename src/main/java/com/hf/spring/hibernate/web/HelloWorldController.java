package com.hf.spring.hibernate.web;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hf.spring.hibernate.entities.Car;
import com.hf.spring.hibernate.entities.Person;
import com.hf.spring.hibernate.service.CarService;

@Controller
@RequestMapping("/helloworld")
//@SessionAttributes(types={Car.class},value={"testString"})
//SessionAttributes只能用于类注解，types和value为并集的关系
public class HelloWorldController {
	
	@Autowired
	CarService carService;
	
	@RequestMapping(value="/hellopost",method=RequestMethod.POST)
	public String helloPost(){
		
		return "success";
	}
	@RequestMapping(value="/hello",
			method=RequestMethod.GET,
			params={"username","age!=10"},
			headers={"Accept-Language=zh-CN,zh;q=0.8"})
	public String hello(){
		System.out.println("hello.......");
		System.out.println(carService.findNum());
		return "success";
	}
	
	@RequestMapping(value="/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id){
		System.out.println("PathVariable......."+id);
		return "success";
	}
	
	@RequestMapping(value="/testRestGet/{id}",method=RequestMethod.GET)
	public String testRestGet(@PathVariable Integer id){
		System.out.println("testRestGet........."+id);
		return "success";
	}
	
	@RequestMapping(value="/testRestPost",method=RequestMethod.POST)
	public String testRestPost(){
		System.out.println("testRestPost.........");
		return "success";
	}
	
	@RequestMapping(value="/testRestPut/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id){
		System.out.println("testRestPut........."+id);
		return "success";
	}
	
	@RequestMapping(value="/testRestDelete/{id}",method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id){
		System.out.println("testRestDelete........."+id);
		return "success";
	}
	
	@RequestMapping(value="/testRequestParam",method=RequestMethod.GET)
	public String testRequestParam(@RequestParam(value="username") String username1,
			@RequestParam(value="age") Integer age1){
		System.out.println("RequestParam..... username:"+username1+",  age:"+age1);
		return "success";
	}
	
	@RequestMapping(value="/testRequestHeaders",method=RequestMethod.GET)
	public String testRequestHeaders(@RequestHeader(value="Accept-Encoding") String a1,
			@RequestHeader(value="Accept-Language") String a2){
		System.out.println("RequestParam..... Accept-Encoding:"+a1+",  Accept-Language:"+a2);
		return "success";
	}
	
	@RequestMapping(value="/testCookieValue",method=RequestMethod.GET)
	public String testCookieValue(@CookieValue(value="JSESSIONID") String sessionId){
		System.out.println("RequestParam..... sessionId:"+sessionId);
		return "success";
	}
	
	@RequestMapping(value="/testPojo",method=RequestMethod.POST)
	public String testPojo(Person person){
		System.out.println("RequestParam..... person:"+person.toString());
		return "success";
	}
	
	@RequestMapping(value="/testServletAPI",method=RequestMethod.GET)
	public void testServletAPI(HttpServletRequest request,
			HttpServletResponse response,
			Writer out,
			HttpSession session) throws IOException{
		System.out.println("RequestParam..... request:"+request+","+response);
		out.write("hello ServletAPI");
		out.write(""+session.getServletContext());
		out.close();
		//return "success";
	}
	
	@RequestMapping(value="/testModelAndView",method=RequestMethod.GET)
	public ModelAndView testModelAndView(){
		String viewName="success";
		ModelAndView modelAndView=new ModelAndView(viewName);
		modelAndView.addObject("time",new Date());
		return modelAndView;
	}
	
	@RequestMapping(value="/testMap",method=RequestMethod.GET)
	public String testMap(Map<String,Object> map){
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("tom","cat"));
		return "success";
	}
	
	@RequestMapping(value="/testSessionAttributes",method=RequestMethod.GET)
	public String testSessionAttributes(Map<String,Object> map){
		Car car=new Car(11, "宝马", 'y', 1.1f);
		map.put("car",car);
		map.put("testString", "字符串。。。");
		return "success";
	}
	
	@RequestMapping(value="/testModelAttribute",method=RequestMethod.POST)
	public String testModelAttribute(Car car){
		System.out.println("editle........");
		System.out.println(car.toString());
		return "success";
	}
	/**
	 * 每个目标方法执行之前被springmvc调用
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getCar(@RequestParam(value="id",required=false) Integer id,
			Map<String,Object> map){
		System.out.println("@ModelAttribute........");
		if(id!=null){
			Car car=carService.find(id+"");
			System.out.println(car.toString());
			map.put("car", car);
		}
	}
}
