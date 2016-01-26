package com.hf.spring.mybits.test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hf.spring.mybits.bean.Car;
import com.hf.spring.mybits.mapper.CarMapper;

public class MybitsTest {
	
	ApplicationContext ctx=null;
	{
		ctx=new ClassPathXmlApplicationContext("spring-context-mybits.xml");
	}

	@Test
	public void test() throws SQLException {
		DataSource dataSource=ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
		CarMapper carMapper=ctx.getBean(CarMapper.class);
		List<Car> cars=carMapper.findAll();
		System.out.println(cars.toString());
	}
}
