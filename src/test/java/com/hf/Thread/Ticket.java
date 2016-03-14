package com.hf.Thread;

public class Ticket implements Runnable{
	
	private int ticket=100;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			synchronized (Ticket.class) {
				try {
					Thread.sleep(500L);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(ticket<=0)
					break;
				System.out.println(Thread.currentThread().getName()+"---Âô³ö"+ticket--);
			}
		}
	}

}
