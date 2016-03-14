package com.hf.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=1;i<5;i++){
			exec.execute(new Demo2());
			Thread.yield();//先检测当前是否有相同优先级的线程处于同可运行状态，如有，则把CPU的占有权交给次线程，否则继续运行原来的线程，所以yield()方法称为“退让”，它把运行机会让给了同等级的其他线程。
			System.out.println(".............");
		}
		exec.shutdown();
	}
}
