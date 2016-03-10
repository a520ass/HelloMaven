package com.hf.mq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
	
	private final static String QUEUE_NAME = "task_queue";
	public static void main(String[] args) throws IOException {
		
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("10.10.3.118");
		factory.setUsername("hefeng3");
		factory.setPassword("0321");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		for(int i=0;i<10;i++){
			String dots = "";  
            for (int j = 0; j <= i; j++)  
            {  
                dots += ".";  
            }  
            String message = "helloworld" + dots+dots.length();  
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
            System.out.println(" [x] Sent '" + message + "'"); 
		}
		
		channel.close();
		connection.close();
	}
}
