package com.hf.spring.springdata.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hf.spring.springdata.entity.Customer;
import com.hf.spring.springdata.entity.Order;
import com.hf.spring.springdata.entity.Person;
import com.hf.spring.springdata.repository.CustomerRepository;
import com.hf.spring.springdata.repository.OrderRepository;
import com.hf.spring.springdata.repository.PersonRepository;

public class SpringDataTest {
	Logger log=LoggerFactory.getLogger(SpringDataTest.class);
	private ApplicationContext ctx=null;
	{
		ctx=new ClassPathXmlApplicationContext("spring-context-springdata.xml");
	}
	
	@Test
	public void test1() throws SQLException{
		DataSource dataSource=ctx.getBean(DataSource.class);
		log.info(dataSource.getConnection()+"");
		PersonRepository personRepository=ctx.getBean(PersonRepository.class);
		//List<Person> person=personRepository.getByEmailNotNull();
		//log.info(person.toString());
		CustomerRepository customerRepository=ctx.getBean(CustomerRepository.class);
		OrderRepository orderRepository=ctx.getBean(OrderRepository.class);
		Customer customer=new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("123@456.com");
		customer.setLastName("FF");
		
		Order order1=new Order();
		order1.setOrderName("GG-G-1");
		
		Order order2=new Order();
		order2.setOrderName("GG-G-2");
		
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		customerRepository.save(customer);
		orderRepository.save(order1);
		orderRepository.save(order2);
		
	}
}
