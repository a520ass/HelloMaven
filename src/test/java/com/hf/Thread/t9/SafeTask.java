package com.hf.Thread.t9;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable{
	
	private static ThreadLocal<Date> startDate=new ThreadLocal<Date>(){

		@Override
		protected Date initialValue() {
			return new Date();
		}
		
	};
	
	@Override
	public void run() {
		//startDate.get()   同一线程内调用此方法。只初始化一次？？
		System.out.printf("Starting Thread:%s:%s\n", Thread.currentThread().getId(),startDate.get()  
                );  
         try {  
        	 double rint = Math.rint(Math.random()*10);
        	// System.err.println(rint);
            TimeUnit.SECONDS.sleep((int)rint);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
           
         System.out.printf("Thread Finished:%s:%s\n", Thread.currentThread().getId(),startDate.get()); 
	
         
         //休眠一段时间后，继续取本地线程变量。值不变
	}

}
