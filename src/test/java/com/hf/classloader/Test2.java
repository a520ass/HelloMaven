package com.hf.classloader;

public class Test2 {
	
	public static final String NAME = "static prames";
	
	static{
		System.out.println("test2静态初始化块执行了！"); 
	}
	
	public void method1(){
		System.out.println("method1方法。。");
	}
	
	private void method2(){
		System.out.println("method2 私有的方法。。");
	}
}
