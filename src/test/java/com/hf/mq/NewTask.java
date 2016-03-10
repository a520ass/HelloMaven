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
		//Message durability持久化
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String[] argv = {"1.2","1.3"};
		String message = getMessage(argv);
		for(int i=0;i<10;i++){
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
			System.out.println(i+" [x] Sent '" + message + "'");
		}
	}
	private static String getMessage(String[] argv) {
		if (argv.length < 1)
		      return "Hello World!";
		    return joinStrings(argv, " ");
	}
	private static String joinStrings(String[] strings, String delimiter) {
		 int length = strings.length;
		    if (length == 0) return "";
		    StringBuilder words = new StringBuilder(strings[0]);
		    for (int i = 1; i < length; i++) {
		      words.append(delimiter).append(strings[i]);
		    }
		    return words.toString();
	}
}
