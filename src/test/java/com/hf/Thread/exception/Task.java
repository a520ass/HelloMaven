package com.hf.Thread.exception;

public class Task implements Runnable{

	@Override
	public void run() {
		//线程run方法中抛出异常
		int numerror=Integer.parseInt("sfsfd");
	}

}
