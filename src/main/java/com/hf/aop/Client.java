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
        ProxyFactory proxyFactory = new ProxyFactory();     // ����������
        proxyFactory.setTarget(new GreetingImpl());         // ����Ŀ�������
        proxyFactory.addAdvice(new GreetingBeforeAdvice()); // ���ǰ����ǿ
        proxyFactory.addAdvice(new GreetingAfterAdvice());  // ��Ӻ�����ǿ 
 
        Greeting greeting = (Greeting) proxyFactory.getProxy(); // �Ӵ������л�ȡ����
        greeting.sayHello("testAop");                              // ���ô���ķ���
    }
	
	@Test
	public void testAop2() {
        ProxyFactory proxyFactory = new ProxyFactory();     // ����������
        proxyFactory.setTarget(new GreetingImpl());         // ����Ŀ�������
        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice()); // �����ǿ
 
        Greeting greeting = (Greeting) proxyFactory.getProxy(); // �Ӵ������л�ȡ����
        greeting.sayHello("testAop");                              // ���ô���ķ���
    }
	
	@Test
	public void testAop4() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-aoptest.xml"); // ��ȡ Spring Context
        com.hf.aop.component.Greeting greeting = (com.hf.aop.component.Greeting) context.getBean("greetingProxy");                        // �� Context �и��� id ��ȡ Bean ������ʵ����һ������
        greeting.sayHello("Jack");                             // ���ô���ķ���
    }
}
