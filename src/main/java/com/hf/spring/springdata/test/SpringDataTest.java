package com.hf.spring.springdata.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.hf.spring.springdata.entity.Person;
import com.hf.spring.springdata.entity.Person_;
import com.hf.spring.springdata.repository.PersonRepository;
import com.hf.spring.springdata.service.PersonService;

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
		PersonService personService=ctx.getBean(PersonService.class);
		//List<Person> person=personRepository.getByEmailNotNull();
		//log.info(person.toString());
		/*CustomerRepository customerRepository=ctx.getBean(CustomerRepository.class);
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
		orderRepository.save(order2);*/
		
		/*log.info(personRepository.getMaxIdPerson().toString());
		System.out.println("--------------------------------");
		log.info(personRepository.testQueryAnnotationParams1("何锋","111").toString());
		System.out.println("--------------------------------");
		log.info(personRepository.testQueryAnnotationParams2("何锋","111").toString());
		System.out.println("--------------------------------");
		log.info(personRepository.testQueryAnnotationLikeParams1("何%","%11").toString());
		
		System.out.println("--------------------------------");
		log.info(personRepository.testQueryAnnotationLikeParams2("何","11").toString());
		
		log.info(personRepository.getTotalCount()+"");
		
		log.info("更新数据的个数"+personService.updatePersonEmail(3, "456@45.com"));*/
		int page=0;//0为第一页
		int size=2;
		//desc从大到小
		org.springframework.data.domain.Sort.Order order1=new org.springframework.data.domain.Sort.Order(Direction.DESC, "id");
		Sort sort=new Sort(order1);
		PageRequest pageRequest=new PageRequest(page, size,sort);
		Page<Person> pagelist=personRepository.findAll(pageRequest);
		System.out.println("总纪录数"+pagelist.getTotalElements());
		System.out.println("当前第几页"+(pagelist.getNumber()+1));
		System.out.println("总页数"+pagelist.getTotalPages());
		System.out.println("当前页面list"+pagelist.getContent());
		System.out.println("当前页面记录数"+pagelist.getNumberOfElements());
	}
	
	/**
	 * 实现带查询条件的分页
	 */
	@Test
	public void testJpaSpecificationExecutor(){
		
		PersonRepository personRepository=ctx.getBean(PersonRepository.class);
		int page=0;//0为第一页
		int size=2;
		//desc从大到小
		org.springframework.data.domain.Sort.Order order1=new org.springframework.data.domain.Sort.Order(Direction.DESC, "id");
		Sort sort=new Sort(order1);
		PageRequest pageable=new PageRequest(page, size,sort);
		Specification<Person> specification=new Specification<Person>() {

			@Override
			public Predicate toPredicate(Root<Person> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				//Path<Person> path=root.get("id");
				//Predicate predicate=cb.gt(root.get("id").as(Integer.class), 1);
				Predicate predicate=cb.gt(root.get(Person_.id), 1);
				return predicate;
			}
		};
		Page<Person> pagelist=personRepository.findAll(specification, pageable);
		
		System.out.println("总纪录数"+pagelist.getTotalElements());
		System.out.println("当前第几页"+(pagelist.getNumber()+1));
		System.out.println("总页数"+pagelist.getTotalPages());
		System.out.println("当前页面list"+pagelist.getContent());
		System.out.println("当前页面记录数"+pagelist.getNumberOfElements());
	}
	
	/**
	 * 测试JpaCriteria
	 */
	@Test
	public void testJpaCriteria(){
		EntityManager em=ctx.getBean(EntityManager.class);
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = criteriaQuery.from(Person.class);
		Predicate predicate = criteriaBuilder.gt(root.get(Person_.id), 1);
		criteriaQuery.where(predicate);
		TypedQuery<Person> typedQuery=em.createQuery(criteriaQuery);
		List<Person> result =typedQuery.getResultList();
		System.out.println(result.toString());
	}
	
	/**
	 * 测试JpaCriteria
	 */
	@Test
	public void testJpaCriteria2(){
		EntityManager em=ctx.getBean(EntityManager.class);
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = criteriaQuery.from(Person.class);
		List<Predicate> predicatesList=new ArrayList<Predicate>();
		predicatesList.add(criteriaBuilder.or(criteriaBuilder.equal(root.get(Person_.id), 1),criteriaBuilder.equal(root.get(Person_.lastName), "陈玲")));
		String queryString="654TE";
		predicatesList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(Person_.email)), StringUtils.upperCase(StringUtils.trim(queryString)) + "%"));
		//predicatesList是and关系   where (person0_.id=1 or person0_.last_name=?) and (upper(person0_.email) like ?)
		criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		TypedQuery<Person> typedQuery=em.createQuery(criteriaQuery);
		List<Person> result =typedQuery.getResultList();
		System.out.println(result.toString());
	}
	
	/**
	 * 测试JpaCriteria
	 * 
	 * 元模型实例通过调用 EntityManager.getMetamodel 方法获得，
	 * EntityType<Employee>的元模型实例通过调用Metamodel.entity(Employee.class)而获得
	 */
	@Test
	public void testJpaCriteria3(){
		EntityManager em=ctx.getBean(EntityManager.class);
		
		Metamodel metamodel = em.getMetamodel();
		EntityType<Person> entityType = metamodel.entity(Person.class);
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = criteriaQuery.from(entityType);
		List<Predicate> predicatesList=new ArrayList<Predicate>();
		predicatesList.add(criteriaBuilder.or(criteriaBuilder.equal(root.get(Person_.id), 1),criteriaBuilder.equal(root.get(Person_.lastName), "陈玲")));
		String queryString="654TE";
		//predicatesList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(Person_.email)), StringUtils.upperCase(StringUtils.trim(queryString)) + "%"));
		//predicatesList是and关系   where (person0_.id=1 or person0_.last_name=?) and (upper(person0_.email) like ?)
		criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		TypedQuery<Person> typedQuery=em.createQuery(criteriaQuery);
		List<Person> result =typedQuery.getResultList();
		System.out.println(result.toString());
	}
	
	/**
	 * 测试JpaCriteria
	 * 
	 * Root.getModel方法获得元模型信息。
	 * 类型 EntityType<Dept>的实例Dept_和name属性
	 * 可以调用getSingularAttribute 方法获得，它与String文本进行比
	 */
	@Test
	public void testJpaCriteria4(){
		EntityManager em=ctx.getBean(EntityManager.class);
		
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = criteriaQuery.from(Person.class);
		
		EntityType<Person> entityType = root.getModel();
		List<Predicate> predicatesList=new ArrayList<Predicate>();
		predicatesList.add(criteriaBuilder.or(criteriaBuilder.equal(root.get(entityType.getSingularAttribute("id", Integer.class)), 1),criteriaBuilder.equal(root.get(Person_.lastName), "陈玲")));
		String queryString="654TE";
		//predicatesList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(Person_.email)), StringUtils.upperCase(StringUtils.trim(queryString)) + "%"));
		//predicatesList是and关系   where (person0_.id=1 or person0_.last_name=?) and (upper(person0_.email) like ?)
		criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		TypedQuery<Person> typedQuery=em.createQuery(criteriaQuery);
		List<Person> result =typedQuery.getResultList();
		System.out.println(result.toString());
	}
	
	/**
	 * 测试JpaCriteria
	 */
	@Test
	public void testJpaCriteria5(){
		EntityManager em=ctx.getBean(EntityManager.class);
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = criteriaQuery.from(Person.class);
		//Predicate predicate = criteriaBuilder.gt(root.get(Person_.id), 1);
		//Expression对象。放在where中
		//1、
		//criteriaQuery.where(root.get(Person_.id).in(1,2,3));
		
		//2、构造Predicate
		Expression<Integer> expression = root.get(Person_.id);
		List<Predicate> predicatesList=new ArrayList<Predicate>();
		predicatesList.add(expression.in(1,2,3));
		criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		
		TypedQuery<Person> typedQuery=em.createQuery(criteriaQuery);
		List<Person> result =typedQuery.getResultList();
		System.out.println(result.toString());
	}
}
