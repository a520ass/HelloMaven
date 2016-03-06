package com.hf.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implements Callable<Boolean>{
	
	public static void main(String[] args) {
		CallableThreadTest callable=new CallableThreadTest();
		FutureTask<Boolean> ft=new FutureTask<>(callable);
		for(int i = 0;i < 100;i++)  
        {  
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);  
            if(i==20)  
            {  
                new Thread(ft,"有返回值的线程").start();  
            }  
        }
		try  
        {  
            System.out.println("子线程的返回值："+ft.get());  
        } catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        } catch (ExecutionException e)  
        {  
            e.printStackTrace();  
        }  
	}
	
	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		int i = 0;  
        for(i=0;i<100;i++)  
        {  
            System.out.println(Thread.currentThread().getName()+" "+i);  
            if(i==50){
            	return false;
            }
        }  
        return true;
	}

}
