package com.hf.Thread;

public class Demo extends Thread{
	public void run(){
		 for(int i=0;i<60;i++){
             System.out.println("demo.Thread."+this.getName()+i);
             //System.out.println(Thread.currentThread().getName()+i);
             try {
      			Thread.sleep(1000L);
      		} catch (InterruptedException e) {
      			e.printStackTrace();
      		}
         }
	}
}
