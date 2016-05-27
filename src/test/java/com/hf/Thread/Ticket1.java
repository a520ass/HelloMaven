package com.hf.Thread;

public class Ticket1 implements Runnable{
	private int ticket=100;
	boolean flag=true;
	@Override
	public void run() {
		while(flag){
			if(ticket<=0){
				flag=false;
			}
			saleTicket();
		}
	}

	private synchronized void saleTicket() {
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			System.out.println(Thread.currentThread().getName()+"---ticket剩余"+ticket--);
	}
}
