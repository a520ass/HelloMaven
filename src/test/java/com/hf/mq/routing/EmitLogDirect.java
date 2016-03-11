package com.hf.mq.routing;

import java.util.Random;
import java.util.UUID;

import com.hf.mq.RabbitMQService;
import com.rabbitmq.client.Channel;

public class EmitLogDirect {
	
	private static final String EXCHANGE_NAME = "ex_logs_direct";  
    private static final String[] SEVERITIES = { "info", "warning", "error" };
	
	public static void main(String[] args) throws Exception {
		Channel channel = RabbitMQService.getChannel();
		// 声明转发器的类型  
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        
      //发送6条消息  
        for (int i = 0; i < 6; i++)  
        {  
            String severity = getSeverity();  
            String message = severity + "_log :" + UUID.randomUUID().toString();  
            // 发布消息至转发器，指定routingkey  
            channel.basicPublish(EXCHANGE_NAME, severity, null, message  
                    .getBytes());  
            System.out.println(" [x] Sent '" + message + "'");  
        }  
  
	}

	private static String getSeverity() {
		// TODO Auto-generated method stub
		Random random = new Random();  
        int ranVal = random.nextInt(3);  
        return SEVERITIES[ranVal];
	}
}
