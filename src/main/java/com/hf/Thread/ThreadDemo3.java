package com.hf.Thread;

public class ThreadDemo3 {
	
	public static void main(String[] args) {
		Ticket1 t =new Ticket1();
		Thread t1 = new Thread(t,"窗口一");
        Thread t2 = new Thread(t,"窗口二");
        Thread t3 = new Thread(t,"窗口三");
        Thread t4 = new Thread(t,"窗口四");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
	}
	
}
