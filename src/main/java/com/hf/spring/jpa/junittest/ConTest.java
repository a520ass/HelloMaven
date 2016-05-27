package com.hf.spring.jpa.junittest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jredis.JredisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.hf.aop.JDKDynamicProxy;
import com.hf.spring.jpa.dao.DepartmentDao;
import com.hf.spring.jpa.dao.EmployeeDao;
import com.hf.spring.jpa.entity.Department;
import com.hf.spring.jpa.entity.Employee;
import com.hf.spring.jpa.entity.User;
import com.hf.spring.jpa.service.DepartmentService;
import com.hf.spring.jpa.service.EmployeeService;
import com.hf.spring.jpa.service.UserService;
import com.hf.spring.jpa.service.impl.UserServiceImpl;

public class ConTest {
	
	ApplicationContext ctx=null;
	
	{
		ctx=new ClassPathXmlApplicationContext("spring-context-jpa.xml");
	}
	
	/*@Test
	public void testDataSource(){
		DataSource dataSource=ctx.getBean(DataSource.class);
		EmployeeService employeeService=ctx.getBean(EmployeeService.class);
		DepartmentService departmentService=ctx.getBean(DepartmentService.class);
		Department department=new Department();
		department.setDepartmentName("���");
		Employee employee1=new Employee(2, "�η�1", "123@123.com", new Date(), new Date(), department);
		Employee employee2=new Employee(3, "�η�2", "123@123.com", new Date(), new Date(), department);
		
		employeeService.saveEmployee(employee1);
		employeeService.saveEmployee(employee2);
		departmentService.saveDepartment(department);//�����ȱ���1��һ��
	}
	*//**
	 * left outer join 1��һ��
	 *//*
	@Test
	public void test1(){
		EmployeeService employeeService=ctx.getBean(EmployeeService.class);
		DepartmentService departmentService=ctx.getBean(DepartmentService.class);
		Employee employee=employeeService.findEmplyeeById(5);
		System.out.println(employee.getLastName());
		System.out.println(employee.getDepartment().getDepartmentName());
	}*/
	/**
	 * ����һ�Զ�
	 */
	/*@Test
	public void test2(){
		EmployeeService employeeService=ctx.getBean(EmployeeService.class);
		DepartmentService departmentService=ctx.getBean(DepartmentService.class);
		Employee employee1=new Employee("�η�1", "123@123.com", new Date(), new Date());
		Employee employee2=new Employee("�η�2", "123@123.com", new Date(), new Date());
		Set<Employee> employees = new HashSet<>();
		employees.add(employee1);
		employees.add(employee2);
		Department department=new Department();
		department.setDepartmentName("yiduiduo");
		department.setEmployees(employees);
		departmentService.saveDepartment(department);
		employeeService.saveEmployee(employee1);
		employeeService.saveEmployee(employee2);
		
	}*/
	@Test
	public void testDataSource(){
		UserService userService=ctx.getBean(UserService.class);
		//����������
		System.out.println(userService.findUserById(1));
		System.out.println(userService.findUserById(1));
		//��ѯ����
		System.out.println(userService.findUserByUsername("superadmin"));
		System.out.println(userService.findUserByUsername("superadmin"));
	}
	
	@Test
	public void testRedis(){
		RedisTemplate redisTemplate=ctx.getBean(RedisTemplate.class);
		redisTemplate.opsForList().leftPush("redis123", "123");
	}
	
	@Test
	public void testEhcache(){
		EhCacheCacheManager bean = ctx.getBean("cacheManager",EhCacheCacheManager.class);
	}
	
	/*@Test
	public void testProxy(){
		UserService userService=ctx.getBean(UserService.class);
		User user=new JDKDynamicProxy(new UserServiceImpl()).getProxy();
	}*/
}
