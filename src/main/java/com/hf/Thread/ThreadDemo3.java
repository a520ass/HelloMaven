package com.hf.Thread;

public class ThreadDemo3 {
	
	public static void main(String[] args) {
		Ticket1 t =new Ticket1();
		Thread t1 = new Thread(t,"����һ");
        Thread t2 = new Thread(t,"���ڶ�");
        Thread t3 = new Thread(t,"������");
        Thread t4 = new Thread(t,"������");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
	}
	
}
