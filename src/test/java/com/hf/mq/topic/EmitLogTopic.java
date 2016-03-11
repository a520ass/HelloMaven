package com.hf.mq.topic;

import java.util.UUID;

import com.hf.mq.RabbitMQService;
import com.rabbitmq.client.Channel;

public class EmitLogTopic {
	private static final String EXCHANGE_NAME = "topic_logs";
	
	public static void main(String[] args) throws Exception {
		Channel channel = RabbitMQService.getChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		
		String[] routing_keys = new String[] { "kernel.info", "cron.warning",  
                "auth.info", "kernel.critical" };  
        for (String routing_key : routing_keys)  
        {  
            String msg = UUID.randomUUID().toString();  
            channel.basicPublish(EXCHANGE_NAME, routing_key, null, msg  
                    .getBytes());  
            System.out.println(" [x] Sent routingKey = "+routing_key+" ,msg = " + msg + ".");  
        }
	}
}
