package com.hf.Thread;

public class ThreadTest {
	
	public static void main(String[] args) {
		Demo d=new Demo();
		d.setName("demoThead");
		d.start();
		 for(int i=0;i<60;i++){
             System.out.println(Thread.currentThread().getName()+i);
             if(i==10){
            	 try {
					d.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             }
            try {
     			Thread.sleep(1000L);
     		} catch (InterruptedException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
         }
	}
}
