package com.hf.spring.jpa.web;

import java.io.FileDescriptor;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hf.spring.jpa.entity.Employee;
import com.hf.spring.jpa.service.DepartmentService;
import com.hf.spring.jpa.service.EmployeeService;

@Controller
public class DepartmentController {
	Logger log=LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,
			Map<String, Object> map){
		if(id!=null){
			Employee employee=employeeService.findEmplyeeById(id);
			//employee.setDepartment(null);
			map.put("employee", employee);
			
		}
	}
	/**
	 * form���ύ Date�������ݰ�(���ʹ�ã�)
	 * <������ϸ����>
	 * @param binder
	 * @see [�ࡢ��#��������#��Ա]
	 *//*
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}*/
	
	
	
	@RequestMapping("/list")
	public String list(Map<String,Object> map,
			HttpServletRequest request,
			HttpServletResponse response){
		log.info(request.getRequestURI());
		map.put("employees", employeeService.findEmployees());
		return "list";
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String,Object> map,
			HttpServletRequest request,
			HttpServletResponse response){
		log.info(request.getRequestURI());
		map.put("departments", departmentService.findDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String save(@Valid Employee employee,
			BindingResult result,
			Map<String, Object> map,
			HttpServletRequest request,
			HttpServletResponse response){
		log.info(request.getRequestURI());
		if(result.getErrorCount()>0){
			for(FieldError error:result.getFieldErrors()){
				log.error(error.getField()+":"+error.getDefaultMessage());
			}
			map.put("departments", departmentService.findDepartments());
			return "input";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id,
			HttpServletRequest request,
			HttpServletResponse response){
		log.info(request.getRequestURI());
		employeeService.deleteEmployee(id);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String,Object> map,
			HttpServletRequest request,
			HttpServletResponse response){
		log.info(request.getRequestURI());
		map.put("departments", departmentService.findDepartments());
		map.put("employee", employeeService.findEmplyeeById(id));
		return "input";
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String update(Employee employee,
			HttpServletRequest request,
			HttpServletResponse response){
		log.info(request.getRequestURI());
		employeeService.saveEmployee(employee);
		return "redirect:/list";
	}
}
