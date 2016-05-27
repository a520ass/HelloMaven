package com.hf.aop;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hf.aop.component.Apology;

public class Client {
	
	@Test
	public void testJdkProxy(){
		 Greeting greeting = new JDKDynamicProxy(new GreetingImpl()).getProxy();
	     greeting.sayHello("testJdkProxy");
	}
	
	@Test
	public void testCglibProxy() throws Exception{
		Greeting greeting = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
        greeting.sayHello("testCglibProxy");
        
        /*Field h = greeting.getClass().getDeclaredField("CGLIB$CALLBACK_0");  
        h.setAccessible(true);  
        Object dynamicAdvisedInterceptor = h.get(greeting);  
          
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");  
        advised.setAccessible(true);  
          
        Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();*/
	}
	
	@Test
	public void testAop() {
        ProxyFactory proxyFactory = new ProxyFactory();     // 创建代理工厂
        proxyFactory.setTarget(new GreetingImpl());         // 射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAdvice()); // 添加前置增强
        proxyFactory.addAdvice(new GreetingAfterAdvice());  // 添加后置增强 
 
        Greeting greeting = (Greeting) proxyFactory.getProxy(); // 从代理工厂中获取代理
        greeting.sayHello("testAop");                              //  调用代理的方法
    }
	
	@Test
	public void testAop2() {
        ProxyFactory proxyFactory = new ProxyFactory();     // �������?��
        proxyFactory.setTarget(new GreetingImpl());         // ����Ŀ�������
        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice()); // �����ǿ
 
        Greeting greeting = (Greeting) proxyFactory.getProxy(); // �Ӵ��?���л�ȡ����
        greeting.sayHello("testAop");                              // ���ô���ķ���
    }
	
	@Test
	public void testAop4() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-aoptest.xml"); // 获取 Spring Context
        com.hf.aop.component.Greeting greeting = (com.hf.aop.component.Greeting) context.getBean("greetingProxy");//  从 Context 中根据 id 获取 Bean 对象（其实就是一个代理）
        greeting.sayHello("Jack");                             // 调用代理的方法
    }
	
	//引入增强
	@Test
	public void testAop5() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-aoptest.xml"); // 获取 Spring Context
			com.hf.aop.component.GreetingImpl greetingImpl = (com.hf.aop.component.GreetingImpl) context.getBean("greetingProxy"); // 注意：转型为目标类，而并非它的 Greeting 接口
	        greetingImpl.sayHello("Jack");
	 
	        Apology apology = (Apology) greetingImpl; // 将目标类强制向上转型为 Apology 接口（这是引入增强给我们带来的特性，也就是“接口动态实现”功能）
	        apology.saySorry("Jack");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
}
