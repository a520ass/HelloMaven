package com.hf.spring.springdata.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hf.spring.springdata.entity.Person;
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
		List<Person> person=personRepository.getByEmailNotNull();
		log.info(person.toString());
	}
}
