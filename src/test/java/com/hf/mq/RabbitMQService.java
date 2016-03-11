package com.hf.mq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQService {
	
	private static Channel channel;
	private static Connection connection;
	
	public static void init(){
		try {
			ConnectionFactory factory=new ConnectionFactory();
			factory.setHost("10.10.3.118");
			factory.setUsername("hefeng3");
			factory.setPassword("0321");
			connection=factory.newConnection();
			channel=connection.createChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Channel getChannel(){
		init();
		return channel;
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		channel.close();
		connection.close();
	}
}
