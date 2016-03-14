package com.hf.Thread;

public class Ticket1 implements Runnable{
	private int ticket=100;
	boolean flag=true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag){
			if(ticket<=0){
				flag=false;
			}
			saleTicket();
		}
	}

	private synchronized void saleTicket() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println(Thread.currentThread().getName()+"---Âô³ö"+ticket--);
	}
}
