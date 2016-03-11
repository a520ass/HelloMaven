package com.hf.mq.routing;

import java.util.Random;

import com.hf.mq.RabbitMQService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveLogsDirect {
	
	private static final String EXCHANGE_NAME = "ex_logs_direct";  
    private static final String[] SEVERITIES = { "info", "warning", "error" };
    
    public static void main(String[] args) throws Exception {
		Channel channel = RabbitMQService.getChannel();
		
		 // 声明direct类型转发器  
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");  
  
        String queueName = channel.queueDeclare().getQueue();  
        String severity = getSeverity();  
        // 指定binding_key  
        channel.queueBind(queueName, EXCHANGE_NAME, severity);  
        System.out.println(" [*] Waiting for "+severity+" logs. To exit press CTRL+C");  
  
        QueueingConsumer consumer = new QueueingConsumer(channel);  
        channel.basicConsume(queueName, true, consumer);  
  
        while (true)  
        {  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
  
            System.out.println(" [x] Received '" + message + "'");  
        }
	}

	private static String getSeverity() {
		Random random = new Random();  
        int ranVal = random.nextInt(3);  
        return SEVERITIES[ranVal];
	}
}
