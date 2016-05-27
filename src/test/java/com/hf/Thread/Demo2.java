package com.hf.Thread;

public class Demo2 implements Runnable{

	@Override
	public void run() {
		synchronized (getClass()) {
			for(int x=0;x<60;x++){
	            System.out.println(Thread.currentThread().getName()+"..."+x);
	        }
		}
	}

}
