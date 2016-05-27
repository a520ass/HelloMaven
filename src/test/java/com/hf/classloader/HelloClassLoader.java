package com.hf.classloader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.junit.Test;

public class HelloClassLoader {
	
	@Test
	public void test1(){
		HelloClassLoader helloClassLoader=new HelloClassLoader();
		Class c=helloClassLoader.getClass();
		//loader和sys是sun.misc.Launcher$AppClassLoader@4537ef34
		//sun.misc.Launcher$AppClassLoader@4537ef34
		ClassLoader loader=c.getClassLoader();
		ClassLoader sys= ClassLoader.getSystemClassLoader();
		URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
		System.out.println(sys);
		//sys.loadClass(name)
		InputStream in=ClassLoader.getSystemClassLoader().getSystemResourceAsStream("java/lang/String.class");
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent());//父级为null、说明是bootstrap classloader ？？
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void test2(){
		ClassLoader loader=HelloClassLoader.class.getClassLoader();
		System.out.println(loader);
		
		try {
			//不会执行static初始化块 
			Class c=loader.loadClass("com.hf.classloader.Test2");
			Test2 t2=(Test2) c.newInstance();//创建实例的时候执行了静态代码块
			Method method=c.getDeclaredMethod("method2", new Class[]{});
			method.setAccessible(true);//反射私有方法需要调用
			method.invoke(t2, new Object[]{});
			//默认会执行初始化块
			//Class.forName("com.hf.classloader.Test2");
			//使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
			//Class.forName("com.hf.classloader.Test2", false, loader);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public HelloClassLoader() {
		super();
		System.out.println("构造函数。。");
	}
	
}
