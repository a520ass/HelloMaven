package com.hf.mq.topic;

import java.io.IOException;

import com.hf.mq.RabbitMQService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveLogsTopicForKernel {
	
	private static final String EXCHANGE_NAME = "topic_logs";
	public static void main(String[] args) throws Exception {
		Channel channel = RabbitMQService.getChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String queueName = channel.queueDeclare().getQueue();
		
		channel.queueBind(queueName, EXCHANGE_NAME, "kernel.*");
		System.out.println(" [*] Waiting for messages about kernel. To exit press CTRL+C");  
		  
        QueueingConsumer consumer = new QueueingConsumer(channel);  
        channel.basicConsume(queueName, true, consumer);
        while (true)  
        {  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
            String routingKey = delivery.getEnvelope().getRoutingKey();  
  
            System.out.println(" [x] Received routingKey = " + routingKey  
                    + ",msg = " + message + ".");  
        } 
		
	}
}
