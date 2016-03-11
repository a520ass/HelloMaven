package com.hf.mq.fanout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class ReceiveLogsToSave {
	
	private final static String EXCHANGE_NAME = "ex_log";
	
	public static void main(String[] args) throws IOException, Exception, ConsumerCancelledException, InterruptedException {
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("10.10.3.118");
		factory.setUsername("hefeng3");
		factory.setPassword("0321");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
  
            print2File(message);
		}
	}

	private static void print2File(String msg) {
		// TODO Auto-generated method stub
		try  
        {  
            String dir = ReceiveLogsToSave.class.getClassLoader().getResource("").getPath();  
            String logFileName = new SimpleDateFormat("yyyy-MM-dd")  
                    .format(new Date());  
            File file = new File(dir, logFileName+".txt");  
            FileOutputStream fos = new FileOutputStream(file, true);  
            fos.write((msg + "\r\n").getBytes());  
            fos.flush();  
            fos.close();  
        } catch (FileNotFoundException e)  
        {  
            e.printStackTrace();  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }
	}
}
