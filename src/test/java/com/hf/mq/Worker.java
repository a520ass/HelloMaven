package com.hf.mq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Worker {
	
	private final static String QUEUE_NAME = "task_queue";
	public static void main(String[] args) throws IOException, Exception, ConsumerCancelledException, InterruptedException {
		//区分不同工作进程的输出  
        int hashCode = Worker.class.hashCode();
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("10.10.3.118");
		factory.setUsername("hefeng3");
		factory.setPassword("0321");
		Connection connection = factory.newConnection();
		
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		//channel.basicQos(1);
		
		QueueingConsumer consumer = new QueueingConsumer(channel);  
        // 指定消费队列  
		boolean ack = false ; //打开应答机制  
		channel.basicConsume(QUEUE_NAME, ack, consumer);  
		 
        while (true)  
        {  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
  
            System.out.println(hashCode + " [x] Received '" + message + "'");  
            doWork(message);  
            System.out.println(hashCode + " [x] Done");  
            
          //另外需要在每次处理完成一个消息后，手动发送一次应答。  
    		channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }  
	}
	protected static void doWork(String message) throws InterruptedException {
		for (char ch: message.toCharArray()) {
	        if (ch == '.') Thread.sleep(1000);
	    }
	}
}
