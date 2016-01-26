package com.hf.spring.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hf.spring.hibernate.dao.CarDao;
import com.hf.spring.hibernate.entities.Car;

@Repository
public class CarDaoImpl implements CarDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//不推荐使用 HibernateTemplate 和 HibernateDaoSupport
	//因为这样会导致 Dao 和 Spring 的 API 进行耦合
	//可以移植性变差
//	private HibernateTemplate hibernateTemplate;
	
	//获取和当前线程绑定的 Session. 
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<Car> findAll() {
		// TODO Auto-generated method stub
		String hsql="select c.id,c.name from Car c";
		Query query=getSession().createQuery(hsql);
		return query.list();
	}

	@Override
	public long findNum() {
		// TODO Auto-generated method stub
		String hsql="SELECT count(c.id) FROM Car c";
		Query query=getSession().createQuery(hsql);
		return (long) query.uniqueResult();
	}

	@Override
	public Car find(String id) {
		// TODO Auto-generated method stub
		String hsql="from Car c where c.id=?";
		Query query=getSession().createQuery(hsql).setString(0, id);
		return (Car) query.uniqueResult();
	}
	
}
