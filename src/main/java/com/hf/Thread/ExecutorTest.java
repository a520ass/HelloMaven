package com.hf.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=1;i<5;i++){
			exec.execute(new Demo2());
			Thread.yield();
			System.out.println(".............");
		}
		exec.shutdown();
	}
}
