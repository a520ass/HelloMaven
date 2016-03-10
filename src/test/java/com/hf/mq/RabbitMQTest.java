package com.hf.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring-context-mq-provider.xml","classpath:spring-context-mq-consumer.xml"})
//不能用spring-context-mq-*.xml  这里是全匹配
public class RabbitMQTest {
	
	@Autowired
	AmqpTemplate amqpTemplate;
	
	@Test
	public void test() throws InterruptedException{
		for(int i=0;i<10;i++){
			amqpTemplate.convertAndSend("queue_one_key","何锋的测试。。发送第"+(i+1)+"个消息");
		}
		Thread.sleep(5000L);
	}

	}
