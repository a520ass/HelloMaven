package com.hf.mq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Worker {
	
	private final static String QUEUE_NAME = "task_queue";
	public static void main(String[] args) throws IOException {
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("10.10.3.118");
		factory.setUsername("hefeng3");
		factory.setPassword("0321");
		Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		channel.basicQos(1);
		
		final Consumer consumer = new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
		        System.out.println(" [x] Received '" + message + "'");
				try {
					doWork(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.out.println(" [x] Done");
					//channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	protected static void doWork(String message) throws InterruptedException {
		for (char ch: message.toCharArray()) {
	        if (ch == '.') Thread.sleep(1000);
	    }
	}
}
