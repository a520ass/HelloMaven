package com.hf.aop;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	
	@Test
	public void testJdkProxy(){
		 Greeting greeting = new JDKDynamicProxy(new GreetingImpl()).getProxy();
	     greeting.sayHello("testJdkProxy");
	}
	
	@Test
	public void testCglibProxy(){
		Greeting greeting = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
        greeting.sayHello("testCglibProxy");
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
}
