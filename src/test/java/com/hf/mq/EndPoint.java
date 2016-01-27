package com.hf.mq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {
	
	protected Channel channel;
	protected Connection connection;
	protected String endPointName;
	
	public EndPoint(String endPointName) throws IOException{
		this.endPointName=endPointName;
		
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("10.10.3.118");
		factory.setUsername("hotusm");
		factory.setPassword("1234");
		connection=factory.newConnection();
		channel=connection.createChannel();
		channel.queueDeclare(endPointName, false, false, false, null);
	}
	
	/**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     * @throws IOException
     */
     public void close() throws IOException{
         this.channel.close();
         this.connection.close();
     }
}
