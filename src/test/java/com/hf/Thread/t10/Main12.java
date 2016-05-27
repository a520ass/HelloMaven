package com.hf.Thread.t10;

public class Main12 {
	
	public static void main(String[] args) {  
		  
        MyTreadFactory factory = new MyTreadFactory("MyTreadFactory");  
        Task12 task = new Task12();  
        Thread thread;  
        System.out.println("Starting the Threads ");  
        for (int i = 0; i < 10; i++) {  
            thread = factory.newThread(task);  
            thread.start();  
        }  
        System.out.println("Factory stats:");  
        System.out.printf("%s\n",factory.getStatuts());  
    }
}
