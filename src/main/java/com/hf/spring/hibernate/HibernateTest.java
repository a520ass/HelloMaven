package com.hf.spring.hibernate;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hf.spring.hibernate.service.CarService;

public class HibernateTest {
	Logger log=LoggerFactory.getLogger(HibernateTest.class);
	ApplicationContext context=null;
	CarService carService=null;
	{
		context=new ClassPathXmlApplicationContext("spring-context.xml");
		carService=context.getBean(CarService.class);
	}
	
	
	
	@Test
	public void test() throws SQLException{
		DataSource dataSource=context.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
		log.info("................."+carService.findNum());
		log.info("..."+carService.findAll());
	}
	/*@Test
	public void test1(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		//Car car=new Car(112, "陈玲", 'y', 1.1f);
		Person person=new Person();
		person.setName("lalal");
		person.setSex('男');
		Car car1=(Car) session.load(Car.class, 112);
		Car car2=(Car) session.load(Car.class, 111);
		List<Car> cars=new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		person.setCars(cars);
		System.out.println(person.toString());
		//session.save(person);
		transaction.commit();
		session.close();
		sessionFactory.close();
		
	}*/
}
