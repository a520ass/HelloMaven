package com.hf.Thread;

public class Ticket implements Runnable{
	
	private int ticket=100;
	@Override
	public void run() {
		while(true){
			synchronized (Ticket.class) {
				try {
					Thread.sleep(500L);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(ticket<=0)
					break;
				System.out.println(Thread.currentThread().getName()+"---"+ticket--);
			}
		}
	}

}
