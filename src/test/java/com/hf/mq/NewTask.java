package com.hf.mq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * RabbitMQ会一个一个的发送信息给下一个消费者(consumer)，而不考虑每个任务的时长等等，且是一次性分配，并非一个一个分配。平均的每个消费者将会获得相等数量的消息。这样分发消息的方式叫做round-robin
 * @author 520
 *
 */
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
