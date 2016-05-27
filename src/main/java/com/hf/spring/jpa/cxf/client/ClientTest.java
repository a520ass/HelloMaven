package com.hf.spring.jpa.cxf.client;

public class ClientTest {
	
	public static void main(String[] args) {
		HelloWorldImplService helloWorldImplService=new HelloWorldImplService();
		HelloWorld helloWorld= helloWorldImplService.getHelloWorldImplPort();
		
		System.err.println(helloWorld);
		System.err.println(helloWorld.sayHi("爱贝贝"));
	}
}
